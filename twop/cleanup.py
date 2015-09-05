import os
import time

total_lines = 0;
erase = raw_input("Erase?")
def remove_class_files(dir):
   global total_lines
   files = [f for f in os.listdir(dir)]
   for f in files:
       try:
           if os.path.isdir(f):
               remove_class_files(dir + "/" + f)
               continue
           if erase == "y":              
               if f[-6:] == ".class":
                   os.remove(dir + "/" + f)
           if f[-5:] == ".java":
              total_lines += len(open(dir + "/" + f, "r").readlines())
       except IndexError:
           continue
remove_class_files(".")
print("Completed, with " + str(total_lines) + " lines of java counted.")
time.sleep(4)
