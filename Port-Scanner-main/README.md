# Port-Scanner (Python)

## Python TCP port scanner
This Port Scanner will work for both the Web Applications as well as remote Host. This tool has been created to provide the basic functionality of a Port Scanner. 
The general concept of Sockets had been used to provide the functionality. 
Port Scanner is built on Python 3 and uses some extra libraries such as socket pyfiglet (for a fancy banner). <br>

**Scan host**
<br>
*Usage:* ./port_scanner.py [IP address] <br>
*Example:* ./port_scanner.py 104.22.55.228

## Output
![portscan](https://user-images.githubusercontent.com/77694789/168617879-0bb6adf9-639f-4aea-ba3e-f4459d509e59.png)

## Note
In the code at line 27 i.e for the port in range(1, 65535): we can custom define our ports under which range we have to scan. This port scanner will generally take the time of 5 mins maximum to produce output in the format, that so and so ports are open or closed.

## Disclaimer
The use of this tool is your responsability. Use parsero to audit your own servers or servers you are allowed to scan. I hereby disclaim any responsibility for actions taken with this tool.
