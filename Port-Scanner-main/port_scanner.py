import pyfiglet
import sys
import socket
from datetime import datetime

# Defining a name
ascii_banner = pyfiglet.figlet_format("PORT SCANNER")
print(ascii_banner)

# Defining a target
if len(sys.argv) == 2:
	
	# translate hostname to IPv4
	ip = socket.gethostbyname(sys.argv[1])
else:
	print("Invalid amount of Argument")

# Add Banner
print("-" * 70)
print("Target Scanning : " + ip)
print("Scanning started at:" + str(datetime.now()))
print("-" * 70)

try:
	
	# scaning ports between 1 to 65,535
	for port in range(1,65535):
		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		socket.setdefaulttimeout(1)
		
		# returns an error indicator
		result = s.connect_ex((ip,port))
		if result ==0:
			print("Hidden services found. \n Port {} is open. \n ".format(port))
		s.close()
		
except KeyboardInterrupt:
		print("\n Quiting Program !!!!")
		sys.exit()
except socket.gaierror:
		print("\n Hostname Could Not Be Resolved !!!!")
		sys.exit()
except socket.error:
		print("\n Server not responding !!!!")
		sys.exit()
