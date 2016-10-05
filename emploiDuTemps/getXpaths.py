import requests
from lxml import html

page = requests.get("https://www.facebook.com/")
root = html.fromstring(page.text)
tree = root.getroottree()
result = root.xpath('//*[. = "XML"]')
for r in result:
	print(tree.getpath(r))