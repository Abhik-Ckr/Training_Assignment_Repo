#!/bin/bash

# Check if URL argument is provided, otherwise use default
if [ -z "$1" ]; then
  echo "No website URL provided. Using default: https://en.wikipedia.org/wiki/Main_Page"
  BASE_URL="https://en.wikipedia.org/wiki/Main_Page"  # Default URL
else
  BASE_URL="$1"  # Take the website URL from the argument
fi

# Array to store the visited URLs
visited_urls=()

# Array to store the entire page content (in memory)
pages_content=()

# Function to recursively download and get links
get_links() {
  local url=$1
  local depth=$2

  # Avoid going too deep, to prevent infinite loops and excessive recursion
  if [ "$depth" -gt 3 ]; then
    return
  fi

  # Avoid revisiting the same URL (to prevent infinite loops)
  for visited_url in "${visited_urls[@]}"; do
    if [ "$visited_url" == "$url" ]; then
      return
    fi
  done

  # Add the current URL to the visited list
  visited_urls+=("$url")

  # Fetch the page content using wget and store it in memory
  echo "Fetching: $url"
  
  # Use wget to download the page content (entire HTML page)
  page_content=$(wget --quiet -O - "$url")

  # Store the entire page content in memory (stored in pages_content array)
  pages_content+=("$page_content")


  # Extract the links from the page content (ignore anchor links)
  new_links=$(echo "$page_content" | grep -oP 'href="\K(\/wiki\/[^"]+)' | sed 's/^/https:\/\/en.wikipedia.org/g')

  # Loop through each link and recursively get more links
  for link in $new_links; do
    # Skip external links and anchor links
    if echo "$link" | grep -qv '^https://en.wikipedia.org/wiki/'; then
      continue
    fi
    # Print the link (you can do something else with it here if needed)
    echo "Found link: $link"
    
    # Recursively get links from the new page
    get_links "$link" $((depth + 1))
  done
}

# Start fetching links from the base URL
get_links "$BASE_URL" 1

echo "All links have been fetched."

# Optionally, show how many pages have been stored in memory
echo "Stored ${#pages_content[@]} pages in memory."
