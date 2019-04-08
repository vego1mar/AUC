import requests
import logging


def fetch_html(full_url):
    req = str()

    try:
        req = requests.get(full_url)
    except requests.HTTPError as exp:
        logging.debug(exp.strerror)
    except requests.ConnectTimeout as exp:
        logging.debug(exp.strerror)
    except requests.TooManyRedirects as exp:
        logging.debug(exp.strerror)

    return req.text


def fetch_file(file_name):
    with open(str(file_name)) as file:
        return str(file.readlines())
