import time

from selenium import webdriver

from selenium.webdriver.chrome.service import Service

service = Service('chromedriver')

service.start()

driver = webdriver.Remote(service.service_url)

driver.get('http://localhost:3030/')

time.sleep(5) 

driver.quit()
