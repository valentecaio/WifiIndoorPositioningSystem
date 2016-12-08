import requests
from lxml import html

page = requests.get("https://ml.internalpositioning.com/dashboard/valentecaio")
root = html.fromstring(page.text)

#print (root)
print (page.text)

#print (root.get_element_by_id("bayescaio"))
