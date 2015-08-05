import os
import time

files = [f for f in os.listdir('.') if os.path.isfile(f)]
line_total = 0
print("Removed files:")
print("--------")
for f in files:
    try:
        if f[-6:] == ".class":
            print("   "+f)
            os.remove(f)
        if f[-5:] == ".java":
            m = open(f, "r")
            line_total += len(m.readlines())
    except IndexError:
        continue
print("--------")
print(str(line_total) + " lines of .java counted.")
time.sleep(7);
