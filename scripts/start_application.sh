#!/bin/bash
for pid in $(ps -e | grep java | awk '{print $1}'); do
  echo "Killing process ID $pid"
  kill -9 $pid
done

for jar_file in *.jar; do
  if [ -f "$jar_file" ]; then
    echo "Executing $jar_file"
    nohup java -jar "$jar_file" > "${jar_file%.jar}.log" 2>&1 &
  else
    echo "No .jar files found in the current directory."
  fi
done