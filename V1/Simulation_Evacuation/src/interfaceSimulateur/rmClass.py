#programme pour effacer les .class et lanceer automatiquement le programme java
import os
import sys

for root, dirs, files in os.walk(sys.path[0]):
    for file in files:
        if file.endswith(".class"):
            os.remove(os.path.join(root, file))


