import requesting as rq
import executing as ex
import triggers as tr


def fetch_html(full_url):
    import requests
    import logging
    req = str()

    try:
        req = requests.get(full_url)
    except requests.HTTPError as exp:
        logging.debug(exp.strerror)
    except requests.ConnectTimeout as exp:
        logging.debug(exp.strerror)
    except requests.TooManyRedirects as exp:
        logging.debug(exp.strerror)
    except requests.exceptions.InvalidSchema as exp:
        logging.debug(exp.strerror)
        return str()

    return req.text


def fetch_file(file_name):
    with open(str(file_name)) as file:
        return str(file.readlines())


def encode_base64(raw_text):
    import base64
    return base64.b64encode(str(raw_text).encode('utf-8'))


def decode_base64(binary_encoded_text):
    import base64
    output = base64.b64decode(str(binary_encoded_text, 'utf-8'))
    return str(output, 'utf-8')


def configure_logging(file_name):
    import logging
    import datetime
    logging.basicConfig(filename=file_name,
                        format='%(asctime)s,%(msecs)d %(name)s %(levelname)s %(message)s',
                        datefmt='%H:%M:%S',
                        level=logging.DEBUG)
    logging.debug('\n\n' + str(datetime.datetime.now()))


def get_tag_type_name(value):
    if str(value) == str(tr.TagType.SIMPLE):
        return "SIMPLE"
    elif str(value) == str(tr.TagType.ATTRIBUTED):
        return "ATTRIBUTED"
    elif str(value) == str(tr.TagType.META):
        return "META"


def get_set_name_str(value):
    if str(value) == str(rq.SpaceName.WEB):
        return "WEB"
    elif str(value) == str(rq.SpaceName.WORK):
        return "WORK"
    elif str(value) == str(rq.SpaceName.LIST):
        return "LIST"


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


def get_entry_for_dobreprogramy_pl(web_space="", ver="_ver", date="_date", size="_size", selector_offset=(0, 0)):
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.RetrieveTags("meta", tr.TagType.META, 30))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(20 + selector_offset[0]))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, ver), tr.FetchAttribute("content"))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(26 + selector_offset[1]))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, date), tr.FetchAttribute("content"))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("divFileSize"))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset(0, 273))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, size), tr.GetRegexMatch(r"[\d]+[.,]+[\d]+ [A-Z]+"))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08)
    return ex.ExecutionOrderEntry(chain_request, web_space)


def get_entry_for_google_play_store(web_space="", app_url="", date="_gp_date", size="_gp_size", ver="_gp_ver"):
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "google_play_apk"), tr.SetWorkspace(app_url))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("Updated"))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset('U', ']'))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetRegexMatch(r">[A-Za-z]+ [\d]+[.,]+ [\d]+<"))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, date), tr.CutAside(1, 1))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("Updated"))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset('U', ']'))
    req_08 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetRegexMatch(r">[\d]+[.,]+[\d]+[\w ]+<"))
    req_09 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, size), tr.CutAside(1, 1))
    req_10 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("Current Version"))
    req_11 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset('C', ']'))
    req_12 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetRegexMatch(r">[\d.,]+<"))
    req_13 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, ver), tr.CutAside(1, 1))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10,
                     req_11, req_12, req_13)
    return ex.ExecutionOrderEntry(chain_request, web_space)


def get_entry_for_ms_store(web_space="", app_url="", size="_ms_size", link="_ms_link"):
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("<h4>"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset(0, 7950))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, size), tr.GetRegexMatch(r"[\d]+[.]+[\d]+ [A-Z]+"))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, link), tr.SetWorkspace(app_url))
    chain_request = (req_01, req_02, req_03, req_04)
    return ex.ExecutionOrderEntry(chain_request, web_space)


def get_entry_for_itunes(web_space="", app_url="", ver="_i_ver", size="_i_size"):
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("data-test-version-number"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset('>', '<'))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, ver), tr.CutAside(1, 1))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("data-test-app-info-size"))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset('>', '<'))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, size), tr.CutAside(1, 1))
    req_07 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "ios_itunes"), tr.SetWorkspace(app_url))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07)
    return ex.ExecutionOrderEntry(chain_request, web_space)


def get_entry_for_majorgeeks(web_space="", date="_mg_date", size="_mg_size"):
    """Retrieves date and size from 'details' page of majorgeeks.com software.\n
       Example: https://www.majorgeeks.com/files/details/jetclean.html\n
    """
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("<strong>Date:"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset(0, 328))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, date), tr.GetRegexMatch(r"[\d]+/[\d]+/[\d]+"))
    req_04 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("<strong>Size:"))
    req_05 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset(0, 300))
    req_06 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, size), tr.GetRegexMatch(r"[\d]+[.,][\d]+ [A-Z]+"))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06)
    return ex.ExecutionOrderEntry(chain_request, web_space)


def get_entry_for_majorgeeks_2(web_space="", link="_mg_link"):
    """Retrieves variant link from 'mirror' page of majorgeeks.com software.\n
       Example: https://www.majorgeeks.com/mg/getmirror/jetclean,1.html\n
    """
    req_01 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("Debug:"))
    req_02 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetSubset(' ', '>'))
    req_03 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, link), tr.CutAside(1, 4))
    chain_request = (req_01, req_02, req_03)
    return ex.ExecutionOrderEntry(chain_request, web_space)
