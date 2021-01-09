import requests

url = 'http://172.17.0.2:8098/buckets/s15237/keys/food'
headers = {'content-type': 'application/json', 'cache-control': 'no-cache'}
get_headers = {'cache-control': 'no-cache'}
pizza = {'name': 'Pizza', 'calories': '500', 'is_vegan': False}
hummus = {'name': 'Hummus', 'calories': '200', 'is_vegan': True}

requests_list = list()
requests_list.append(requests.put(url, data=pizza, headers=headers))
requests_list.append(requests.get(url, headers=get_headers))
requests_list.append(requests.put(url, data=hummus, headers=headers))
requests_list.append(requests.get(url, headers=get_headers))
requests_list.append(requests.delete(url, headers=get_headers))
requests_list.append(requests.get(url, headers=get_headers))

f = open('komunikaty.txt', 'w')

for r in requests_list:
    f.write('[' + str(r.status_code) + ']\n')
    f.write(r.text)
    f.write('\n\n')

f.close()


