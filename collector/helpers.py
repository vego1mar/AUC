from urllib.request import urlopen


def fetch_html(url):
    with urlopen(str(url)) as connection:
        return connection.read()


def fetch_file(file_name):
    with open(str(file_name)) as file:
        return str(file.readlines())
