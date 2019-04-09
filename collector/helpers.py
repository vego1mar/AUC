import requests
import logging
import base64
import datetime


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


def encode_base64(raw_text):
    return base64.b64encode(str(raw_text).encode('utf-8'))


def decode_base64(binary_encoded_text):
    output = base64.b64decode(str(binary_encoded_text, 'utf-8'))
    return str(output, 'utf-8')


def configure_logging(file_name):
    logging.basicConfig(filename=file_name,
                        format='%(asctime)s,%(msecs)d %(name)s %(levelname)s %(message)s',
                        datefmt='%H:%M:%S',
                        level=logging.DEBUG)
    logging.debug('\n\n' + str(datetime.datetime.now()))


def get_tag_type_name(value):
    from .triggers import TagType

    if str(value) == str(TagType.SIMPLE):
        return "SIMPLE"
    elif str(value) == str(TagType.ATTRIBUTED):
        return "ATTRIBUTED"
    elif str(value) == str(TagType.META):
        return "META"
