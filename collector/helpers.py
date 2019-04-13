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


def get_entry_for_dobreprogramy_pl(web_space="", ver="_ver", date="_date", size="_size", selector_offset=0):
    from .requesting import SpaceName, Target, InvocationRequest
    from .triggers import RetrieveTags, TagType, SelectElement, FetchAttribute, Find, GetSubset, GetRegexMatch
    from .executing import ExecutionOrderEntry
    req_01 = InvocationRequest(Target(SpaceName.WEB), RetrieveTags("meta", TagType.META, 30))
    req_02 = InvocationRequest(Target(SpaceName.LIST), SelectElement(20 + selector_offset))
    req_03 = InvocationRequest(Target(SpaceName.WORK, True, ver), FetchAttribute("content"))
    req_04 = InvocationRequest(Target(SpaceName.LIST), SelectElement(26 + selector_offset))
    req_05 = InvocationRequest(Target(SpaceName.WORK, True, date), FetchAttribute("content"))
    req_06 = InvocationRequest(Target(SpaceName.WEB), Find("divFileSize"))
    req_07 = InvocationRequest(Target(SpaceName.WORK), GetSubset(0, 273))
    req_08 = InvocationRequest(Target(SpaceName.WORK, True, size), GetRegexMatch(r"[\d]+[.,]+[\d]+ [A-Z]+"))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08)
    return ExecutionOrderEntry(chain_request, web_space)


def get_entry_for_google_play_store(web_space="", app_url="", date="_gp_date", size="_gp_size", ver="_gp_ver"):
    from .requesting import SpaceName, Target, InvocationRequest
    from .triggers import SetWorkspace, CutAside, Find, GetSubset, GetRegexMatch
    from .executing import ExecutionOrderEntry
    req_01 = InvocationRequest(Target(SpaceName.WORK, True, "google_play_apk"), SetWorkspace(app_url))
    req_02 = InvocationRequest(Target(SpaceName.WEB), Find("Updated"))
    req_03 = InvocationRequest(Target(SpaceName.WORK), GetSubset('U', ']'))
    req_04 = InvocationRequest(Target(SpaceName.WORK), GetRegexMatch(r">[A-Za-z]+ [\d]+[.,]+ [\d]+<"))
    req_05 = InvocationRequest(Target(SpaceName.WORK, True, date), CutAside(1, 1))
    req_06 = InvocationRequest(Target(SpaceName.WEB), Find("Updated"))
    req_07 = InvocationRequest(Target(SpaceName.WORK), GetSubset('U', ']'))
    req_08 = InvocationRequest(Target(SpaceName.WORK), GetRegexMatch(r">[\d]+[.,]+[\d]+[\w ]+<"))
    req_09 = InvocationRequest(Target(SpaceName.WORK, True, size), CutAside(1, 1))
    req_10 = InvocationRequest(Target(SpaceName.WEB), Find("Current Version"))
    req_11 = InvocationRequest(Target(SpaceName.WORK), GetSubset('C', ']'))
    req_12 = InvocationRequest(Target(SpaceName.WORK), GetRegexMatch(r">[\d.,]+<"))
    req_13 = InvocationRequest(Target(SpaceName.WORK, True, ver), CutAside(1, 1))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07, req_08, req_09, req_10,
                     req_11, req_12, req_13)
    return ExecutionOrderEntry(chain_request, web_space)


def get_entry_for_ms_store(web_space="", app_url="", size="_ms_size", link="_ms_link"):
    from .requesting import Target, SpaceName, InvocationRequest
    from .triggers import GetRegexMatch, SetWorkspace, GetSubset, Find
    from .executing import ExecutionOrderEntry
    req_01 = InvocationRequest(Target(SpaceName.WEB), Find("<h4>"))
    req_02 = InvocationRequest(Target(SpaceName.WORK), GetSubset(0, 7950))
    req_03 = InvocationRequest(Target(SpaceName.WORK, True, size), GetRegexMatch(r"[\d]+[.]+[\d]+ [A-Z]+"))
    req_04 = InvocationRequest(Target(SpaceName.WORK, True, link), SetWorkspace(app_url))
    chain_request = (req_01, req_02, req_03, req_04)
    return ExecutionOrderEntry(chain_request, web_space)


def get_entry_for_itunes(web_space="", app_url="", ver="_i_ver", size="_i_size"):
    from .requesting import Target, SpaceName, InvocationRequest
    from .triggers import CutAside, SetWorkspace, GetSubset, Find
    from .executing import ExecutionOrderEntry
    req_01 = InvocationRequest(Target(SpaceName.WEB), Find("data-test-version-number"))
    req_02 = InvocationRequest(Target(SpaceName.WORK), GetSubset('>', '<'))
    req_03 = InvocationRequest(Target(SpaceName.WORK, True, ver), CutAside(1, 1))
    req_04 = InvocationRequest(Target(SpaceName.WEB), Find("data-test-app-info-size"))
    req_05 = InvocationRequest(Target(SpaceName.WORK), GetSubset('>', '<'))
    req_06 = InvocationRequest(Target(SpaceName.WORK, True, size), CutAside(1, 1))
    req_07 = InvocationRequest(Target(SpaceName.WORK, True, "ios_itunes"), SetWorkspace(app_url))
    chain_request = (req_01, req_02, req_03, req_04, req_05, req_06, req_07)
    return ExecutionOrderEntry(chain_request, web_space)
