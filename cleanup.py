import os
import time

def remove_class_files(dir):
   files = [f for f in os.listdir(dir)]
   for f in files:
       try:
           if os.path.isdir(f):
               remove_class_files(dir + "/" + f)
               continue
           if f[-6:] == ".class":
               os.remove(dir + "/" + f)
       except IndexError:
           continue
remove_class_files(".")
print("Completed")