//Longest Substring Without Repeating Characters 
//Given a string, find the length of the longest substring without repeating characters.
#include <bits/stdc++.h>
using namespace std;
int lengthOfLongestSubstring(string s) {
    int i = 0;
    int j = 0;
    int maxLen = 0;
    unordered_map<char, int> mpp;
    while(j < s.size()){
        if(mpp.find(s[j]) != mpp.end() && i <= mpp[s[j]]){
            i = mpp[s[j]] + 1;
        }
        maxLen = max(maxLen, j - i + 1);
        mpp[s[j]] = j;
        j++;
    }
    return maxLen;
}

int main()
{
    string s = "acbabcbba";
    cout<<"for input -> "<< s <<" <- the length of the longest substring without repeating characters => ";
    int result = lengthOfLongestSubstring(s);
    cout<<result;

    return 0;
}