import re as reg
import executing as ex
import requesting as rq
import helpers as hp
import triggers as tr


def _get_indent():
    return '\t'


def _get_object_opening():
    return '{\n'


def _get_object_closing():
    return '}\n'


def _get_attribute(attribute):
    return '"' + str(attribute) + '": '


def _get_string_value(value):
    if str(value) == "True":
        return "true"
    if str(value) == "False":
        return "false"

    return str(value)


def _get_value(value, is_string=True, use_comma=False):
    addend = ''

    if is_string:
        addend = '"'

    if use_comma:
        return addend + _get_string_value(value) + addend + ',\n'

    return addend + _get_string_value(value) + addend + '\n'


def _get_value_line(attribute, value, is_string_value=True, use_comma=True):
    return _get_attribute(attribute) + _get_value(value, is_string_value, use_comma)


def _get_comma_ended_json_for_recursive_objects(json_str):
    length = len(json_str) - 1
    return json_str[0:length] + ',\n'


def _get_indent_offset(prepend_indent_no=0):
    return prepend_indent_no + 1


def _get_json_for_closed_recursive_objects(json_str, prepend):
    length = len(json_str) - 2
    cancel_comma_end = json_str[0:length]
    closed_json = cancel_comma_end + '\n' + prepend + _get_object_closing()
    return closed_json


def target_to_json(target, prepend_indent_no=0):
    if not isinstance(target, rq.Target):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("set_name", SpaceNameHelper.get_set_name_str(target.set_name))
    json_str += prepend + _get_indent() + _get_value_line("is_gathering_request", target.is_gathering_request, False)
    json_str += prepend + _get_indent() + _get_value_line("collectible_name", target.collectible_name, True, False)
    json_str += prepend + _get_object_closing()
    return json_str


def _get_json_for_trigger_text_attribute_only(trigger, prepend_indent_no=0):
    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("text", trigger._text, use_comma=False)
    json_str += prepend + _get_object_closing()
    return json_str


def find_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.Find):
        return None

    return _get_json_for_trigger_text_attribute_only(trigger, prepend_indent_no)


def find_next_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.FindNext):
        return None

    return _get_json_for_trigger_text_attribute_only(trigger, prepend_indent_no)


def retrieve_tags_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.RetrieveTags):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("tag_name", trigger._name)
    json_str += prepend + _get_indent() + _get_value_line("tag_type", TagTypeHelper.get_tag_type_name(trigger._type))
    json_str += prepend + _get_indent() + _get_value_line("amount", trigger._amount, False, False)
    json_str += prepend + _get_object_closing()
    return json_str


def select_element_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.SelectElement):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("position", trigger._position, False, False)
    json_str += prepend + _get_object_closing()
    return json_str


def fetch_attribute_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.FetchAttribute):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("name", trigger._name, use_comma=False)
    json_str += prepend + _get_object_closing()
    return json_str


def get_regex_match_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.GetRegexMatch):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("pattern", trigger._regex, use_comma=False)
    json_str += prepend + _get_object_closing()
    return json_str


def cut_aside_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.CutAside):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("left", trigger._left, is_string_value=False)
    json_str += prepend + _get_indent() + _get_value_line("right", trigger._right, False, False)
    json_str += prepend + _get_object_closing()
    return json_str


def set_workspace_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.SetWorkspace):
        return None

    return _get_json_for_trigger_text_attribute_only(trigger, prepend_indent_no)


def get_subset_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.GetSubset):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_for_get_subset_trigger(trigger._begin, prepend, "begin")
    json_str += prepend + _get_indent() + _get_value_for_get_subset_trigger(trigger._end, prepend, "end", False)
    json_str += prepend + _get_object_closing()
    return json_str


def _get_value_for_get_subset_trigger(value, prepend, attribute, use_comma=True):
    if isinstance(value, int):
        return prepend + _get_indent() + _get_value_line(attribute, value, False, use_comma)

    return prepend + _get_indent() + _get_value_line(attribute, value, True, use_comma)


def add_text_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.AddText):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("prepend", trigger._prepend_text)
    json_str += prepend + _get_indent() + _get_value_line("append", trigger._append_text, True, False)
    json_str += prepend + _get_object_closing()
    return json_str


def delete_trigger_to_json(trigger, prepend_indent_no=0):
    if not isinstance(trigger, tr.Delete):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("string", trigger._string)
    json_str += prepend + _get_indent() + _get_value_line("characters", trigger._characters, True, False)
    json_str += prepend + _get_object_closing()
    return json_str


def invocation_request_to_json(request, prepend_indent_no=0):
    if not isinstance(request, rq.InvocationRequest):
        return None

    prepend = _get_indent() * prepend_indent_no
    offset = _get_indent_offset(prepend_indent_no)
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_attribute("target") + target_to_json(request.target, offset)[offset:]
    json_str = _get_comma_ended_json_for_recursive_objects(json_str)
    json_str += prepend + _get_indent() + _get_attribute(hp.get_trigger_name(request.trigger))
    json_str += prepend + trigger_to_json(request.trigger, offset)[offset:]
    json_str += prepend + _get_object_closing()
    return json_str


def trigger_to_json(trigger, prepend_indent_no=0):
    if isinstance(trigger, tr.Find):
        return find_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.FindNext):
        return find_next_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.RetrieveTags):
        return retrieve_tags_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.FetchAttribute):
        return fetch_attribute_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.SelectElement):
        return select_element_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.SetWorkspace):
        return set_workspace_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.GetRegexMatch):
        return get_regex_match_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.CutAside):
        return cut_aside_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.GetSubset):
        return get_subset_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.AddText):
        return add_text_trigger_to_json(trigger, prepend_indent_no)
    if isinstance(trigger, tr.Delete):
        return delete_trigger_to_json(trigger, prepend_indent_no)


def chain_request_to_json(chain_request, prepend_indent_no=0):
    if not isinstance(chain_request, (tuple, type([]))):
        return None

    prepend = _get_indent() * prepend_indent_no
    offset = _get_indent_offset(prepend_indent_no)
    no = int(1)
    json_str = prepend + _get_object_opening()

    for request in chain_request:
        json_str += prepend + _get_indent() + _get_attribute("request_" + str(no))
        json_str += invocation_request_to_json(request, offset)[offset:]
        json_str = _get_comma_ended_json_for_recursive_objects(json_str)
        no += 1

    json_str = _get_json_for_closed_recursive_objects(json_str, prepend)
    return json_str


def execution_order_entry_to_json(entry, prepend_indent_no=0):
    if not isinstance(entry, ex.ExecutionOrderEntry):
        return None

    prepend = _get_indent() * prepend_indent_no
    offset = _get_indent_offset(prepend_indent_no)
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_attribute("chain_request")
    json_str += chain_request_to_json(entry.chain_request, offset)[offset:]
    json_str = _get_comma_ended_json_for_recursive_objects(json_str)
    json_str += prepend + _get_indent() + _get_value_line("html_data", entry.html_data, use_comma=False)
    json_str += prepend + _get_object_closing()
    return json_str


def execution_order_to_json(execution_order, prepend_indent_no=0):
    if not isinstance(execution_order, ex.ExecutionOrder):
        return None

    prepend = _get_indent() * prepend_indent_no
    offset = _get_indent_offset(prepend_indent_no)
    no = int(1)
    json_str = prepend + _get_object_opening()

    for entry in execution_order:
        json_str += prepend + _get_indent() + _get_attribute("entry_" + str(no))
        json_str += execution_order_entry_to_json(entry, offset)[offset:]
        json_str = _get_comma_ended_json_for_recursive_objects(json_str)
        no += 1

    json_str = _get_json_for_closed_recursive_objects(json_str, prepend)
    return json_str


class SpaceNameHelper:
    WEB = 'WEB'
    WORK = 'WORK'
    LIST = 'LIST'

    @staticmethod
    def get_set_name_str(obj):
        if str(obj) == str(rq.SpaceName.WEB):
            return SpaceNameHelper.WEB
        if str(obj) == str(rq.SpaceName.WORK):
            return SpaceNameHelper.WORK
        if str(obj) == str(rq.SpaceName.LIST):
            return SpaceNameHelper.LIST

    @staticmethod
    def get_set_name_obj(name):
        if str(name) == SpaceNameHelper.WEB:
            return rq.SpaceName.WEB
        if str(name) == SpaceNameHelper.WORK:
            return rq.SpaceName.WORK
        if str(name) == SpaceNameHelper.LIST:
            return rq.SpaceName.LIST


class JSONDecoder:
    def __init__(self, json_str):
        self.json_str = str(json_str)

    def json_to_target(self):
        target_str = str(self.json_str)
        set_name_attr = Algorithms.get_attribute_value(target_str, TargetNames.SET_NAME)
        is_request_attr = Algorithms.get_attribute_value(target_str, TargetNames.IS_GATHERING_REQUEST)
        set_name = SpaceNameHelper.get_set_name_obj(set_name_attr)
        is_gathering_request = hp.get_boolean_obj(is_request_attr)
        collectible_name = Algorithms.get_attribute_value(target_str, TargetNames.COLLECTIBLE_NAME)
        target = rq.Target(set_name, is_gathering_request, collectible_name)
        return target

    def json_to_trigger(self):
        parser = JSONTriggerParser(self.json_str)
        parser.parse()
        return parser.get_trigger()


class TargetNames:
    SET_NAME = 'set_name'
    IS_GATHERING_REQUEST = 'is_gathering_request'
    COLLECTIBLE_NAME = 'collectible_name'


class TriggerNames:
    FIND = "find_trigger"
    FIND_NEXT = "find_next_trigger"
    RETRIEVE_TAGS = "retrieve_tags_trigger"
    FETCH_ATTRIBUTE = "fetch_attribute_trigger"
    SELECT_ELEMENT = "select_element_trigger"
    SET_WORKSPACE = "set_workspace_trigger"
    GET_REGEX_MATCH = "get_regex_match_trigger"
    CUT_ASIDE = "cut_aside_trigger"
    GET_SUBSET = "get_subset_trigger"
    ADD_TEXT = "add_text_trigger"
    DELETE = "delete_trigger"


class TriggerFieldsNames:
    TEXT = 'text'
    TAG_NAME = 'tag_name'
    TAG_TYPE = 'tag_type'
    AMOUNT = 'amount'
    NAME = 'name'


class IJSONTriggerParser:
    def parse(self):
        raise NotImplementedError

    def get_trigger(self):
        raise NotImplementedError


class JSONFindTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.text = None

    def parse(self):
        self.text = Algorithms.get_attribute_value(self._json_str, TriggerFieldsNames.TEXT)
        self._result_trigger = tr.Find(self.text)

    def get_trigger(self):
        return self._result_trigger


class JSONFindNextTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.text = None

    def parse(self):
        self.text = Algorithms.get_attribute_value(self._json_str, TriggerFieldsNames.TEXT)
        self._result_trigger = tr.FindNext(self.text)

    def get_trigger(self):
        return self._result_trigger


class TagTypeHelper:
    SIMPLE = 'SIMPLE'
    ATTRIBUTED = 'ATTRIBUTED'
    META = 'META'

    @staticmethod
    def get_tag_type_name(obj):
        if str(obj) == str(tr.TagType.SIMPLE):
            return TagTypeHelper.SIMPLE
        if str(obj) == str(tr.TagType.ATTRIBUTED):
            return TagTypeHelper.ATTRIBUTED
        if str(obj) == str(tr.TagType.META):
            return TagTypeHelper.META

    @staticmethod
    def get_tag_type_obj(name):
        if str(name) == TagTypeHelper.SIMPLE:
            return tr.TagType.SIMPLE
        if str(name) == TagTypeHelper.ATTRIBUTED:
            return tr.TagType.ATTRIBUTED
        if str(name) == TagTypeHelper.META:
            return tr.TagType.META


class JSONRetrieveTagsTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.tag_name = None
        self.tag_type = None
        self.amount = None

    def parse(self):
        self.tag_name = Algorithms.get_attribute_value(self._json_str, TriggerFieldsNames.TAG_NAME)
        self.tag_type = Algorithms.get_attribute_value(self._json_str, TriggerFieldsNames.TAG_TYPE)
        tag_type_obj = TagTypeHelper.get_tag_type_obj(self.tag_type)
        self.amount = Algorithms.get_number_value(self._json_str, TriggerFieldsNames.AMOUNT)
        self._result_trigger = tr.RetrieveTags(self.tag_name, tag_type_obj, self.amount)

    def get_trigger(self):
        return self._result_trigger


class JSONFetchAttributeTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.name = None

    def parse(self):
        self.name = Algorithms.get_attribute_value(self._json_str, TriggerFieldsNames.NAME)
        self._result_trigger = tr.FetchAttribute(self.name)

    def get_trigger(self):
        return self._result_trigger


class JSONSelectElementTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.position = None

    def parse(self):
        raise NotImplementedError

    def get_trigger(self):
        raise NotImplementedError


class JSONSetWorkspaceTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.text = None

    def parse(self):
        raise NotImplementedError

    def get_trigger(self):
        raise NotImplementedError


class JSONGetRegexMatchTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.pattern = None

    def parse(self):
        raise NotImplementedError

    def get_trigger(self):
        raise NotImplementedError


class JSONCutAsideTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.left = None
        self.right = None

    def parse(self):
        raise NotImplementedError

    def get_trigger(self):
        raise NotImplementedError


class JSONGetSubsetTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.begin = None
        self.end = None

    def parse(self):
        raise NotImplementedError

    def get_trigger(self):
        raise NotImplementedError


class JSONAddTextTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.prepend = None
        self.append = None

    def parse(self):
        raise NotImplementedError

    def get_trigger(self):
        raise NotImplementedError


class JSONDeleteTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._result_trigger = None
        self.string = None
        self.characters = None

    def parse(self):
        raise NotImplementedError

    def get_trigger(self):
        raise NotImplementedError


class JSONTriggerParser(IJSONTriggerParser):
    def __init__(self, json_str):
        self._json_str = str(json_str)
        self._parser_chooser = JSONTriggerParserChooser()
        self._parser_chooser.set_json_str(self._json_str)
        self.trigger_name = Algorithms.get_trigger_name(self._json_str)
        self._parser = self._parser_chooser.get_trigger_parser(self.trigger_name)

    def parse(self):
        self._parser.parse()

    def get_trigger(self):
        func = {'get': self._parser.get_trigger}
        trigger = func['get']()
        return trigger


class JSONTriggerParserChooser:
    def __init__(self):
        self._json_str = str()
        self._functions = dict()
        self._set_functions()

    def set_json_str(self, json_str):
        self._json_str = str(json_str)

    def _set_functions(self):
        self._functions[TriggerNames.FIND] = self._get_find_trigger_parser
        self._functions[TriggerNames.FIND_NEXT] = self._get_find_next_trigger_parser
        self._functions[TriggerNames.RETRIEVE_TAGS] = self._get_retrieve_tags_trigger_parser
        self._functions[TriggerNames.FETCH_ATTRIBUTE] = self._get_fetch_attribute_trigger_parser
        self._functions[TriggerNames.SELECT_ELEMENT] = self._get_select_element_trigger_parser
        self._functions[TriggerNames.SET_WORKSPACE] = self._get_set_workspace_trigger_parser
        self._functions[TriggerNames.GET_REGEX_MATCH] = self._get_get_regex_match_trigger_parser
        self._functions[TriggerNames.CUT_ASIDE] = self._get_cut_aside_trigger_parser
        self._functions[TriggerNames.GET_SUBSET] = self._get_get_subset_trigger_parser
        self._functions[TriggerNames.ADD_TEXT] = self._get_add_text_trigger_parser
        self._functions[TriggerNames.DELETE] = self._get_delete_trigger_parser

    def _get_find_trigger_parser(self):
        return JSONFindTriggerParser(self._json_str)

    def _get_find_next_trigger_parser(self):
        return JSONFindNextTriggerParser(self._json_str)

    def _get_retrieve_tags_trigger_parser(self):
        return JSONRetrieveTagsTriggerParser(self._json_str)

    def _get_fetch_attribute_trigger_parser(self):
        return JSONFetchAttributeTriggerParser(self._json_str)

    def _get_select_element_trigger_parser(self):
        raise NotImplementedError

    def _get_set_workspace_trigger_parser(self):
        raise NotImplementedError

    def _get_get_regex_match_trigger_parser(self):
        raise NotImplementedError

    def _get_cut_aside_trigger_parser(self):
        raise NotImplementedError

    def _get_get_subset_trigger_parser(self):
        raise NotImplementedError

    def _get_add_text_trigger_parser(self):
        raise NotImplementedError

    def _get_delete_trigger_parser(self):
        raise NotImplementedError

    def get_trigger_parser(self, trigger_name):
        return self._functions[trigger_name]()


class Algorithms:
    @staticmethod
    def get_trigger_name(json_str):
        """Find text within the first pair of quotes."""
        idx_begin = str(json_str).find('"')
        offset = idx_begin + 1
        idx_end = str(json_str[offset:]).find('"')
        lhs = idx_begin + 1
        rhs = offset + idx_end
        return json_str[lhs:rhs]

    @staticmethod
    def get_attribute_value(json_str, key_str):
        """Search for the specified key and then find subsequent text within a pair of quotes."""
        key_idx = str(json_str).find(str(key_str))
        key_offset = key_idx + len(key_str) + 1
        key_offset_str = str(json_str[key_offset:])
        value_begin_idx = key_offset_str.find('"')
        line_end_idx = key_offset_str.find('\n')
        line_end_str = key_offset_str[0:line_end_idx]
        value_end_idx = line_end_str.rfind('"')
        lhs = value_begin_idx + 1
        return line_end_str[lhs:value_end_idx]

    @staticmethod
    def get_number_value(json_str, key_str):
        """Search for the line with the specified key_str value and find the first regex group of digits."""
        key_idx = json_str.find(key_str)
        key_end_line = str(json_str[key_idx:]).find('\n')
        rhs = key_idx + key_end_line
        value_line_str = json_str[key_idx:rhs]
        pattern = reg.compile(r'[\d]+')
        number = pattern.search(value_line_str).group(0)
        return int(number)
