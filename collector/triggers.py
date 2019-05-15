import logging
import json
import re
import requesting as rq
import helpers as hp


class TriggerNames:
    TRIGGER = 'trigger'
    FIND = 'find_trigger'
    FIND_NEXT = 'find_next_trigger'
    RETRIEVE_TAGS = 'retrieve_tags_trigger'
    SELECT_ELEMENT = 'select_element_trigger'
    FETCH_ATTRIBUTE = 'fetch_attribute_trigger'
    GET_REGEX_MATCH = 'get_regex_match_trigger'
    CUT_ASIDE = 'cut_aside_trigger'
    SET_WORKSPACE = 'set_workspace_trigger'
    GET_SUBSET = 'get_subset_trigger'
    ADD_TEXT = 'add_text_trigger'
    DELETE = 'delete_trigger'


class Trigger(json.JSONEncoder):
    """This should be treated as an abstract class."""
    TRIGGER = 'trigger_type'

    def __init__(self):
        super().__init__(indent=hp.get_json_indent())
        self.trigger_type = TriggerNames.TRIGGER
        self._result = str()
        self._result_list = list()

    def get_result(self):
        return self._result

    def set_result(self, result):
        self._result = result

    def get_result_list(self):
        return self._result_list

    def set_result_list(self, result_list):
        self._result_list = result_list

    def invoke(self, target, set_spaces):
        raise NotImplementedError

    def from_json(self, json_str):
        raise NotImplementedError

    def to_json(self):
        raise NotImplementedError

    def from_dict(self, dct):
        raise NotImplementedError

    def to_dict(self):
        raise NotImplementedError

    @staticmethod
    def get_obj(trigger_dict):
        if trigger_dict[Trigger.TRIGGER] == TriggerNames.FIND:
            return Find(trigger_dict[Find.TEXT])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.FIND_NEXT:
            return FindNext(trigger_dict[FindNext.TEXT])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.RETRIEVE_TAGS:
            tag_name = trigger_dict[RetrieveTags.TAG_NAME]
            tag_type = TagTypeHelper.get_tag_type_obj(trigger_dict[RetrieveTags.TAG_TYPE])
            amount = trigger_dict[RetrieveTags.AMOUNT]
            return RetrieveTags(tag_name, tag_type, amount)

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.SELECT_ELEMENT:
            return SelectElement(trigger_dict[SelectElement.POSITION])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.FETCH_ATTRIBUTE:
            return FetchAttribute(trigger_dict[FetchAttribute.ATTR_NAME])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.GET_REGEX_MATCH:
            return GetRegexMatch(trigger_dict[GetRegexMatch.PATTERN])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.CUT_ASIDE:
            return CutAside(trigger_dict[CutAside.LEFT], trigger_dict[CutAside.RIGHT])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.SET_WORKSPACE:
            return SetWorkspace(trigger_dict[SetWorkspace.TEXT])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.GET_SUBSET:
            return GetSubset(trigger_dict[GetSubset.BEGIN], trigger_dict[GetSubset.END])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.ADD_TEXT:
            return AddText(trigger_dict[AddText.PREPEND], trigger_dict[AddText.APPEND])

        if trigger_dict[Trigger.TRIGGER] == TriggerNames.DELETE:
            return Delete(trigger_dict[Delete.STRING], trigger_dict[Delete.CHARACTERS])

    def compare(self, obj):
        raise NotImplementedError


class Find(Trigger):
    """Finds the first occurrence of text.\n
       text_to_find="YY"\n
       work_space_before="aaaYYbbb"\n
       work_space_after="YYbbb"
    """
    TEXT = 'text'

    def __init__(self, text):
        super(Find, self).__init__()
        self.trigger_type = TriggerNames.FIND
        self.text = str(text)

    def default(self, o):
        self.to_dict()

    def invoke(self, target, set_spaces):
        self._find_text(target, set_spaces)

    def _find_text(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._find_text_in_string(set_spaces.web_space)
        elif target.set_name == rq.SpaceName.WORK:
            self._find_text_in_string(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _find_text_in_string(self, string_to_search_in):
        text_index = str(string_to_search_in).find(self.text)

        if not text_index == -1:
            self.set_result(string_to_search_in[text_index:])

    @classmethod
    def from_dict(cls, dct):
        return Find(dct[Find.TEXT])

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[Find.TEXT])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = TriggerNames.FIND
        this[Find.TEXT] = self.text
        return this

    def to_json(self):
        return self.encode(self.to_dict())

    def compare(self, obj):
        if not isinstance(obj, Find):
            return False

        return self.text == obj.text


class FindNext(Trigger):
    """Finds the second occurrence of text.\n
       text_to_find="YY"\n
       work_space_before="aaaYYbbbYYcccd"\n
       work_space_after="YYcccd"
    """
    TEXT = 'text'

    def __init__(self, text):
        super(FindNext, self).__init__()
        self.trigger_type = TriggerNames.FIND_NEXT
        self.text = str(text)

    def invoke(self, target, set_spaces):
        self._find_next(target, set_spaces)

    def _find_next(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._find_next_text(set_spaces.web_space)
        elif target.set_name == rq.SpaceName.WORK:
            self._find_next_text(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _find_next_text(self, string):
        index_1 = str(string).find(self.text)
        offset = index_1 + len(self.text)
        index_2 = str(string[offset:]).find(self.text)

        if not (index_1 == -1 or index_2 == -1):
            self.set_result(string[index_2 + offset:])

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[FindNext.TEXT])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        return FindNext(dct[FindNext.TEXT])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = TriggerNames.FIND_NEXT
        this[FindNext.TEXT] = self.text
        return this

    def compare(self, obj):
        if not isinstance(obj, FindNext):
            return False

        return self.text == obj.text


class TagType:
    SIMPLE = 1,
    ATTRIBUTED = 2,
    META = 3


class TagTypeHelper:
    SIMPLE = 'SIMPLE'
    ATTRIBUTED = 'ATTRIBUTED'
    META = 'META'

    @staticmethod
    def get_tag_type_name(obj):
        if str(obj) == str(TagType.SIMPLE):
            return TagTypeHelper.SIMPLE
        if str(obj) == str(TagType.ATTRIBUTED):
            return TagTypeHelper.ATTRIBUTED
        if str(obj) == str(TagType.META):
            return TagTypeHelper.META

    @staticmethod
    def get_tag_type_obj(name):
        if str(name) == TagTypeHelper.SIMPLE:
            return TagType.SIMPLE
        if str(name) == TagTypeHelper.ATTRIBUTED:
            return TagType.ATTRIBUTED
        if str(name) == TagTypeHelper.META:
            return TagType.META


class RetrieveTags(Trigger):
    """Extract and get tag content from HTML string.\n
       RetrieveTags(tag_name="p", tag_type=TagType.SIMPLE, amount=1)\n
       work_space_before="<section><p>A paragraph.</p></section>"\n
       work_space_after="<p>A paragraph.</p>"\n
       RetrieveTags(tag_name="a", tag_type=TagType.ATTRIBUTED, amount=1)\n
       work_space_before="<b><a href="www"></a></b>"\n
       work_space_after="<a href="www"></a>")\n
       RetrieveTags(tag_name="meta", tag_type=TagType.META, amount=1)\n
       work_space_before="<div><meta charset="utf-8"/></div>"\n
       work_space_after="<meta charset="utf-8"/>"
    """
    TAG_NAME = 'tag_name'
    TAG_TYPE = 'tag_type'
    AMOUNT = 'amount'

    def __init__(self, tag_name, tag_type, amount):
        super(RetrieveTags, self).__init__()
        self.tag_name = str(tag_name)
        self.tag_type = tag_type
        self.amount = int(amount)

    def invoke(self, target, set_spaces):
        self._retrieve_tags(target, set_spaces)

    def _retrieve_tags(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._retrieve_tags_from(set_spaces.web_space)
        elif target.set_name == rq.SpaceName.WORK:
            self._retrieve_tags_from(set_spaces.work_space)
        else:
            return

        set_spaces.list_space = self.get_result_list()

    def _retrieve_tags_from(self, string):
        source = string
        opening_tag = '<' + self.tag_name
        closing_tag = '</' + self.tag_name + '>'
        tags_list = list()

        if self.tag_type == TagType.SIMPLE:
            opening_tag += '>'
        elif self.tag_type == TagType.META:
            closing_tag = '>'

        for _ in range(0, self.amount):
            opening_index = str(source).find(opening_tag)

            if opening_index == -1:
                break

            offset = opening_index + len(opening_tag)
            closing_index = str(source[offset:]).find(closing_tag)

            if closing_index == -1:
                tags_list.append(source[offset:])
                continue

            tag_right_slice = offset + closing_index + len(closing_tag)
            tags_list.append(source[opening_index:tag_right_slice])
            source = source[tag_right_slice:]

        self.set_result_list(tags_list)

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        tag_name = json_dict[RetrieveTags.TAG_NAME]
        tag_type = TagTypeHelper.get_tag_type_obj(json_dict[RetrieveTags.TAG_TYPE])
        amount = json_dict[RetrieveTags.AMOUNT]
        return cls(tag_name, tag_type, amount)

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        tag_name = dct[RetrieveTags.TAG_NAME]
        tag_type = dct[RetrieveTags.TAG_TYPE]
        amount = dct[RetrieveTags.AMOUNT]
        return cls(tag_name, tag_type, amount)

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = TriggerNames.RETRIEVE_TAGS
        this[RetrieveTags.TAG_NAME] = self.tag_name
        this[RetrieveTags.TAG_TYPE] = TagTypeHelper.get_tag_type_name(self.tag_type)
        this[RetrieveTags.AMOUNT] = self.amount
        return this

    def compare(self, obj):
        if not isinstance(obj, RetrieveTags):
            return False

        result_1 = self.tag_type == obj.tag_type
        result_2 = self.tag_type == obj.tag_type
        result_3 = self.amount == obj.amount
        return result_1 and result_2 and result_3


class SelectElement(Trigger):
    """Copy LIST_SPACE element pointed out by a position to WORK_SPACE.\n
       list_space=['a', 'b', 'c']\n
       SelectElement(position=0)\n
       work_space_after='a'\n
       SelectElement(position=2)\n
       work_space_after='c'
    """
    POSITION = 'position'

    def __init__(self, position):
        super(SelectElement, self).__init__()
        self.trigger_type = TriggerNames.SELECT_ELEMENT
        self.position = int(position)

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.LIST:
            self._select_element(set_spaces)
            set_spaces.work_space = self.get_result()
            set_spaces.list_space = self.get_result_list()

    def _select_element(self, set_spaces):
        if len(set_spaces.list_space) >= self.position + 1:
            selection = set_spaces.list_space[self.position]
            self.set_result(selection)
            self.set_result_list(set_spaces.list_space)

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[SelectElement.POSITION])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        return cls(dct[SelectElement.POSITION])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = self.trigger_type
        this[SelectElement.POSITION] = self.position
        return this

    def compare(self, obj):
        if not isinstance(obj, SelectElement):
            return False

        return self.position == obj.position


class FetchAttribute(Trigger):
    """Searches for the first occurrence of an HTML tag attribute.\n
       FetchAttribute(name="id")\n
       work_space_before="<section class="glx-section" id="glx-library"><p>Text</p></section>"\n
       work_space_after="glx-library"
    """
    ATTR_NAME = 'attr_name'

    def __init__(self, attr_name):
        super(FetchAttribute, self).__init__()
        self.trigger_type = TriggerNames.FETCH_ATTRIBUTE
        self.attr_name = attr_name

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._fetch_attribute_value(set_spaces.web_space)
        elif target.set_name == rq.SpaceName.WORK:
            self._fetch_attribute_value(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _fetch_attribute_value(self, string):
        attribute_begin = str(self.attr_name) + '="'
        attribute_end = '"'
        index_begin = str(string).find(attribute_begin)

        if index_begin == -1:
            return

        offset = index_begin + len(attribute_begin)
        new_string = string[offset:]
        index_end = str(new_string).find(attribute_end)

        if index_end == -1:
            self.set_result(new_string)
            return

        self.set_result(new_string[0:index_end])

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[FetchAttribute.ATTR_NAME])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        return cls(dct[FetchAttribute.ATTR_NAME])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = self.trigger_type
        this[FetchAttribute.ATTR_NAME] = self.attr_name
        return this

    def compare(self, obj):
        if not isinstance(obj, FetchAttribute):
            return False

        return self.attr_name == obj.attr_name


class GetRegexMatch(Trigger):
    """It does a regex search within a single string and returns its group (the matched substring).\n
       regex="[\\d]+.[\\d]+.[\\d]+.[\\d]+"\n
       work_space_before="/galaxy_client_1.2.54.27.pkg"\n
       work_space_after="1.2.54.27"
       """
    PATTERN = 'pattern'

    def __init__(self, pattern):
        super(GetRegexMatch, self).__init__()
        self.trigger_type = TriggerNames.GET_REGEX_MATCH
        self.pattern = pattern

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._get_regex_search_group(set_spaces.web_space)
        elif target.set_name == rq.SpaceName.WORK:
            self._get_regex_search_group(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _get_regex_search_group(self, text):
        self.set_result(str())

        try:
            matched_substring = re.search(self.pattern, text).group(0)
            self.set_result(matched_substring)
        except AttributeError:
            logging.debug("Regular expression group not found; regex=" + self.pattern)

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[GetRegexMatch.PATTERN])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        return cls(dct[GetRegexMatch.PATTERN])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = self.trigger_type
        this[GetRegexMatch.PATTERN] = self.pattern
        return this

    def compare(self, obj):
        if not isinstance(obj, GetRegexMatch):
            return False

        return self.pattern == obj.pattern


class CutAside(Trigger):
    """Deletes some characters from string on both sides of it.\n
       CutAside(left=11, right=11)\n
       work_space_before="abcdefghijklmnopqrstuvwxyz"\n
       work_space_after="lmno"\n
       CutAside(left=0, right=4)\n
       work_space_before="catfish"\n
       work_space_after="cat"
    """
    LEFT = 'left'
    RIGHT = 'right'

    def __init__(self, left, right):
        super(CutAside, self).__init__()
        self.trigger_type = TriggerNames.CUT_ASIDE
        self.left = int(left)
        self.right = int(right)

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._cut_aside(set_spaces.web_space)
        elif target.set_name == rq.SpaceName.WORK:
            self._cut_aside(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _cut_aside(self, text):
        if self.left > len(str(text)):
            self.set_result(str())
            return

        left_cut = str(text)[self.left:]

        if self.right > len(str(left_cut)):
            self.set_result(str())
            return

        offset = len(left_cut) - self.right
        right_cut = str(left_cut)[0:offset]
        self.set_result(str(right_cut))

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[CutAside.LEFT], json_dict[CutAside.RIGHT])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        cls(dct[CutAside.LEFT], dct[CutAside.RIGHT])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = self.trigger_type
        this[CutAside.LEFT] = self.left
        this[CutAside.RIGHT] = self.right
        return this

    def compare(self, obj):
        if not isinstance(obj, CutAside):
            return False

        result_1 = self.left == obj.left
        result_2 = self.right == obj.right
        return result_1 and result_2


class SetWorkspace(Trigger):
    """Simply sets the work space.\n
       SetWorkspace(text="This is the work space now.")\n
       work_space_before="ABCDEFGHIJKLMNOPQRSTUVWXYZ"\n
       work_space_after="This is the work space now."\n
       """
    TEXT = 'text'

    def __init__(self, text):
        super(SetWorkspace, self).__init__()
        self.trigger_type = TriggerNames.SET_WORKSPACE
        self.text = text

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WORK:
            self.set_result(self.text)
            set_spaces.work_space = self.get_result()

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[SetWorkspace.TEXT])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        return cls(dct[SetWorkspace.TEXT])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = self.trigger_type
        this[SetWorkspace.TEXT] = self.text
        return this

    def compare(self, obj):
        if not isinstance(obj, SetWorkspace):
            return False

        return self.text == obj.text


class GetSubset(Trigger):
    """Extracts a substring.\n
       GetSubset(begin=3, end=8)\n
       work_space_before="entanglement"\n
       work_space_after="angle"\n
       GetSubset(begin="float", end='d')\n
       work_space_before="The period can also occur in floating-point and imaginary literals."\n
       work_space_after="floating-point and"
    """
    BEGIN = 'begin'
    END = 'end'

    def __init__(self, begin, end):
        super(GetSubset, self).__init__()
        self.trigger_type = TriggerNames.GET_SUBSET
        self.begin = begin
        self.end = end

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._get_subset(set_spaces.web_space)
        if target.set_name == rq.SpaceName.WORK:
            self._get_subset(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _get_subset(self, text):
        if isinstance(self.begin, int) and isinstance(self.end, int):
            self._get_substring_integerify(text)
        elif isinstance(self.begin, str) and isinstance(self.end, str):
            self._get_substring_stringify(text)

    def _get_substring_integerify(self, text):
        start = int(self.begin)
        meta = int(self.end)

        if start > meta or meta > len(text):
            return ValueError('start > meta OR meta > len(text)')

        self.set_result(text[start:meta])

    def _get_substring_stringify(self, text):
        begin_index = str(text).find(self.begin)

        if begin_index == -1:
            self.set_result(str())
            return

        end_index = str(text[begin_index:]).find(self.end) + begin_index + len(self.end)

        if end_index == -1:
            self.set_result(str())
            return

        if begin_index > end_index:
            self.set_result(text[end_index:begin_index])
            return

        self.set_result(text[begin_index:end_index])

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[GetSubset.BEGIN], json_dict[GetSubset.END])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        return cls(dct[GetSubset.BEGIN], dct[GetSubset.END])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = self.trigger_type
        this[GetSubset.BEGIN] = self.begin
        this[GetSubset.END] = self.end
        return this

    def compare(self, obj):
        if not isinstance(obj, GetSubset):
            return False

        result_1 = self.begin == obj.begin
        result_2 = self.end == obj.end
        return result_1 and result_2


class AddText(Trigger):
    """Prepends and appends text to string.\n
       AddText(prepend="123_", append="_321")\n
       work_space_before="text"\n
       work_space_after="123_text_321"
    """
    PREPEND = 'prepend'
    APPEND = 'append'

    def __init__(self, prepend, append):
        super(AddText, self).__init__()
        self.trigger_type = TriggerNames.ADD_TEXT
        self.prepend = prepend
        self.append = append

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WORK:
            self.set_result(self.prepend + str(set_spaces.work_space) + self.append)
            set_spaces.work_space = self.get_result()

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[AddText.PREPEND], json_dict[AddText.APPEND])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        return cls(dct[AddText.PREPEND], dct[AddText.APPEND])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = self.trigger_type
        this[AddText.PREPEND] = self.prepend
        this[AddText.APPEND] = self.append
        return this

    def compare(self, obj):
        if not isinstance(obj, AddText):
            return False

        result_1 = self.prepend == obj.prepend
        result_2 = self.append == obj.append
        return result_1 and result_2


class Delete(Trigger):
    """Removes strings and characters from a string.\n
       Delete(string=" mauris ", characters="")\n
       work_space_before="Lectus mauris ultrices eros in."\n
       work_space_after="Lectusultrices eros in."\n
       Delete(string="", characters="la e.")\n
       work_space_before="Nulla malesuada pellentesque elit eget."\n
       work_space_after="Numsudpntsquitgt"\n
       Delete(string="id diam m", characters="idams")\n
       work_space_before="Pharetra sit amet aliquam id diam maecenas."\n
       work_space_after="Phretr t et lqua ecen."
    """
    STRING = 'string'
    CHARACTERS = 'characters'

    def __init__(self, string, characters):
        super(Delete, self).__init__()
        self.trigger_type = TriggerNames.DELETE
        self.string = string
        self.characters = characters

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WORK:
            self._delete_strings(set_spaces.work_space)
            self._delete_characters(self.get_result())
            set_spaces.work_space = self.get_result()

    def _delete_strings(self, source_str):
        if self.string == "":
            self.set_result(source_str)
            return

        target_str = str(source_str)

        while True:
            str_index = target_str.find(self.string)

            if str_index == -1:
                break

            end_index = str_index + len(self.string)
            target_str = target_str[:str_index] + target_str[end_index:]

        self.set_result(target_str)

    def _delete_characters(self, source_str):
        if self.characters == "":
            self.set_result(source_str)
            return

        target_str = str(source_str)

        for i in range(0, len(self.characters)):
            target_str = hp.remove_characters(target_str, self.characters[i])

        self.set_result(target_str)

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.loads(json_str)
        return cls(json_dict[Delete.STRING], json_dict[Delete.CHARACTERS])

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_dict(cls, dct):
        return cls(dct[Delete.STRING], dct[Delete.CHARACTERS])

    def to_dict(self):
        this = dict()
        this[Trigger.TRIGGER] = self.trigger_type
        this[Delete.STRING] = self.string
        this[Delete.CHARACTERS] = self.characters
        return this

    def compare(self, obj):
        if not isinstance(obj, Delete):
            return False

        result_1 = self.string == obj.string
        result_2 = self.characters == obj.characters
        return result_1 and result_2
