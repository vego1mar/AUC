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
    elif str(value) == "False":
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


def target_to_json(target, prepend_indent_no=0):
    if not isinstance(target, rq.Target):
        return None

    prepend = _get_indent() * prepend_indent_no
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_value_line("set_name", hp.get_set_name_str(target.set_name))
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
    json_str += prepend + _get_indent() + _get_value_line("name", trigger._name)
    json_str += prepend + _get_indent() + _get_value_line("type", hp.get_tag_type_name(trigger._type))
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
    json_str += prepend + _get_indent() + _get_value_line("name", trigger._regex, use_comma=False)
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
    json_str += prepend + _get_indent() + _get_value_line("prepend_text", trigger._prepend_text)
    json_str += prepend + _get_indent() + _get_value_line("append_text", trigger._append_text, True, False)
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
    offset = prepend_indent_no + 1
    json_str = prepend + _get_object_opening()
    json_str += prepend + _get_indent() + _get_attribute("target") + target_to_json(request.target, offset)[offset:]
    json_str = json_str[0:(len(json_str) - 1)] + ',\n'
    json_str += prepend + _get_indent() + _get_attribute(_get_trigger_name(request.trigger))
    json_str += prepend + trigger_to_json(request.trigger, offset)[offset:]
    json_str += prepend + _get_object_closing()
    return json_str


def trigger_to_json(trigger, prepend_indent_no=0):
    if isinstance(trigger, tr.Find):
        return find_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.FindNext):
        return find_next_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.RetrieveTags):
        return retrieve_tags_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.FetchAttribute):
        return fetch_attribute_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.SelectElement):
        return select_element_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.SetWorkspace):
        return set_workspace_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.GetRegexMatch):
        return get_regex_match_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.CutAside):
        return cut_aside_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.GetSubset):
        return get_subset_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.AddText):
        return add_text_trigger_to_json(trigger, prepend_indent_no)
    elif isinstance(trigger, tr.Delete):
        return delete_trigger_to_json(trigger, prepend_indent_no)


def _get_trigger_name(trigger):
    if isinstance(trigger, tr.Find):
        return "find_trigger"
    elif isinstance(trigger, tr.FindNext):
        return "find_next_trigger"
    elif isinstance(trigger, tr.RetrieveTags):
        return "retrieve_tags_trigger"
    elif isinstance(trigger, tr.FetchAttribute):
        return "fetch_attribute_trigger"
    elif isinstance(trigger, tr.SelectElement):
        return "select_element_trigger"
    elif isinstance(trigger, tr.SetWorkspace):
        return "set_workspace_trigger"
    elif isinstance(trigger, tr.GetRegexMatch):
        return "get_regex_match_trigger"
    elif isinstance(trigger, tr.CutAside):
        return "cut_aside_trigger"
    elif isinstance(trigger, tr.GetSubset):
        return "get_subset_trigger"
    elif isinstance(trigger, tr.AddText):
        return "add_text_trigger"
    elif isinstance(trigger, tr.Delete):
        return "delete_trigger"
