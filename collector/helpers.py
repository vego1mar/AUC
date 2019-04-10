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


def remove_characters(source_str, char):
    new_str = str(source_str) + '\0'
    deleted = 0

    for i in range(0, len(source_str)):
        if source_str[i] in char:
            lhs = i - deleted
            rhs = i - deleted + 1
            new_str = new_str[:lhs] + new_str[rhs:]
            deleted += 1

    valid_len = len(new_str) - 1
    return new_str[0:valid_len]


def get_web_space(base64_file_path):
    file_1 = fetch_file(base64_file_path)
    rhs = len(str(file_1)) - 2
    base64str = file_1[2:rhs]
    return decode_base64(base64str.encode('ascii'))
