# Linux Assignment - Abhik Chakraborty
### Please open "Linux_Assignment_Abhik_Chakraborty" for more detailed operview.
Date: 27th May 2025

---

### Instructions and Answers:

1. **Make scraper.sh executable**
```bash
chmod +x scraper.sh
```

2. **Run scraper.sh on multiple Wikipedia pages**
```bash
./scraper.sh "https://en.wikipedia.org/wiki/India" &
./scraper.sh "https://en.wikipedia.org/wiki/Wikipedia:Stub" &
```

3. **List all processes running**
```bash
ps aux
```

4. **Top 5 memory-consuming processes**
```bash
ps aux --sort=-%mem | head -n 6
```

5. **HTTP response code from google.com**
```bash
curl -o /dev/null -s -w "%{http_code}\n" https://www.google.com
```

6. **Top 3 CPU-consuming processes**
```bash
ps aux --sort=-%cpu | head -n 4
```

7. **Find files > 100MB**
```bash
find -type f -size +100M
```

8. **Python version**
```bash
python --version
```

9. **Current Time Zone (Windows)**
```bash
cmd.exe /c tzutil /g
```

10. **System uptime (Windows)**
```bash
systeminfo | findstr "System Boot Time"
```

11. **Find all ERROR logs**
```bash
grep "ERROR" log.txt > errors.txt
```

12. **Events between 09:10–09:19**
```bash
awk '$2 >= "09:10:00" && $2 <= "09:19:59"' log.txt
```

13. **Count INFO logs**
```bash
grep -c "INFO" log.txt
```

14. **First 5 lines of log**
```bash
head -n 5 log.txt
```

15. **Last 5 lines of log**
```bash
tail -n 5 log.txt
```

16. **Filter error/fail/warning**
```bash
grep -Ei "error|fail|warning" log.txt
```

17. **Rename file**
```bash
mv log.txt new_log.txt
```

18. **Add 'hello, world!' at top**
```bash
sed -i '1i hello, world!' new_log.txt
```

19. **Delete file**
```bash
rm log.txt
```

20. **Filter INFO by date**
```bash
grep "2025-02-19" new_log.txt | grep "INFO"
```

21. **Monitor file for 'Error'**
```bash
tail -f new_log.txt | grep --line-buffered "Error"
```

22. **Curl google.com and save output**
```bash
curl https://www.google.com -o google_output.txt
```

23. **Make file read-only**
```bash
chmod 444 example.txt
```

24. **Make script executable (myscript.sh)**
```bash
chmod +x myscript.sh
```

25. **Make all files in dir readable**
```bash
chmod -R a+r ~/Desktop/linux
```

26. **Find all .log files**
```bash
find . -type f -name "*.log"
```

27. **Kernel version**
```bash
uname -r
```

28. **IP address (Windows)**
```bash
ipconfig
```

29. **List files/dirs by size**
```bash
du -ah --max-depth=1 | sort -hr
```

30. **List files in human-readable format**
```bash
ls -lh
```

31. **Hostname**
```bash
hostname
```

32. **Kill process by PID**
```bash
kill 1234
```

33. **Create test1.txt & test2.txt and merge**
```bash
echo "hello" > test1.txt
echo "world" > test2.txt
cat test1.txt test2.txt > test.txt
```

34. **test.sh to create multiple processes**
```bash
#!/bin/bash
num_processes=10
for ((i=1; i<=num_processes; i++))
do
  (sleep 60) &
  echo "Created process with PID: $!"
done
```

35. **Kill specific PIDs**
```bash
kill 1964 1963
```

36. **Shell script to greet with name**
```bash
echo -e '#!/bin/bash\nread -p "Enter your name: " name\necho "Hello $name"' > greet.sh
chmod +x greet.sh
./greet.sh
```

37. **Download ZIP from GitHub**
```bash
wget https://github.com/github/gitignore/archive/refs/heads/main.zip
```

38. **Zip and unzip a file**
```bash
zip test.zip test.txt
unzip test.zip
```

---

**End of Assignment**
