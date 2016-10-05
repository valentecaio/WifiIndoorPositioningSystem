from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from time import sleep
from selenium.webdriver.common.action_chains import ActionChains

import requests
from lxml import html

time_to_wait = 4

# get user from file
file = open("loginCAS.txt", 'r')
user = file.read(8)
file.read(1)
pwrd = file.read(11)

# open driver and go to website
driver = webdriver.Firefox()
driver.get("https://login.telecom-bretagne.eu/cas/login?service=http%3A%2F%2Fedt.telecom-bretagne.eu%2Fdirect%2Findex.jsp")

# login
elem = driver.find_element_by_id("username")
elem.clear()
elem.send_keys(user)
elem = driver.find_element_by_id("password")
elem.clear()
elem.send_keys(pwrd)
elem = driver.find_element_by_name("submit")
elem.click()

sleep(time_to_wait)

# select year 
elem_2016 = "x-auto-15"
elem = driver.find_element_by_id(elem_2016)
elem.click()
elem_okButton = "x-btn-text"
elem = driver.find_element_by_class_name(elem_okButton)
elem.click()

sleep(time_to_wait)

# select students
elem_etudiantes = "x-tree3-node-joint"
elem = driver.find_element_by_class_name(elem_etudiantes)
elem.click()

sleep(2)

# select ig 2a
elem = driver.find_element_by_id("Direct Planning Tree_x-auto-210")
actions = ActionChains(driver)
actions.move_to_element(elem)
actions.double_click(elem)
actions.perform()
sleep(2)



sleep(2)


'''
# pega os iniciais com sucesso
# e.text
elemsIni = driver.find_elements_by_xpath("//*[contains(@id, 'Direct Planning Tree_x-auto')]")
'''

'''
elemsInLevel = driver.find_elements_by_xpath("//*[contains(@id, 'Direct Planning Tree_x-auto') and @aria-level='3']")

for e in elemsInLevel:
	print (e.text, e.tag_name, e.location)
'''

sleep(2*time_to_wait)
driver.close()


'''
<html>
 <body>
  <form id="loginForm">
   <input name="username" type="text" />
   <input name="password" type="password" />
   <input name="continue" type="submit" value="Login" />
   <input name="continue" type="button" value="Clear" />
  </form>
</body>
<html>

login_form = driver.find_element_by_xpath("/html/body/form[1]")
login_form = driver.find_element_by_xpath("//form[1]")
login_form = driver.find_element_by_xpath("//form[@id='loginForm']")

username = driver.find_element_by_xpath("//form[input/@name='username']")
username = driver.find_element_by_xpath("//form[@id='loginForm']/input[1]")
username = driver.find_element_by_xpath("//input[@name='username']")
'''

