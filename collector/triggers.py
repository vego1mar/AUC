import logging
import re
import json_assist as ja
import requesting as rq
import helpers as hp


class Trigger:
    """This should be treated as an abstract class."""

    def __init__(self):
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

    def to_string(self):
        raise NotImplementedError

    def to_json(self):
        raise NotImplementedError


class Find(Trigger):
    """Finds the first occurrence of text.\n
       text_to_find="YY"\n
       work_space_before="aaaYYbbb"\n
       work_space_after="YYbbb"
    """

    def __init__(self, text):
        super().__init__()
        self._text = str(text)

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
        text_index = str(string_to_search_in).find(self._text)

        if not text_index == -1:
            self.set_result(string_to_search_in[text_index:])
        else:
            logging.debug("Text not found; text=" + self._text)

    def to_string(self):
        return Find.__name__ + '(name=' + self._text + ')'

    def to_json(self):
        return ja.find_trigger_to_json(self)


class FindNext(Trigger):
    """Finds the second occurrence of text.\n
       text_to_find="YY"\n
       work_space_before="aaaYYbbbYYcccd"\n
       work_space_after="YYcccd"
    """

    def __init__(self, text):
        super().__init__()
        self._text = str(text)

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
        index_1 = str(string).find(self._text)
        offset = index_1 + len(self._text)
        index_2 = str(string[offset:]).find(self._text)

        if index_1 == -1 or index_2 == -1:
            logging.debug("Text not found; text=" + self._text)
        else:
            self.set_result(string[index_2 + offset:])

    def to_string(self):
        return FindNext.__name__ + '(text=' + self._text + ')'

    def to_json(self):
        return ja.find_next_trigger_to_json(self)


class TagType:
    SIMPLE = 1,
    ATTRIBUTED = 2,
    META = 3


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

    def __init__(self, tag_name, tag_type, amount):
        super().__init__()
        self._name = str(tag_name)
        self._type = tag_type
        self._amount = int(amount)

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
        opening_tag = '<' + self._name
        closing_tag = '</' + self._name + '>'
        tags_list = list()

        if self._type == TagType.SIMPLE:
            opening_tag += '>'
        elif self._type == TagType.META:
            closing_tag = '>'

        for _ in range(0, self._amount):
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

    def to_string(self):
        return RetrieveTags.__name__ + '(name=' + self._name + ', type=' + hp.get_tag_type_name(self._type) \
               + ', amount=' + str(self._amount) + ')'

    def to_json(self):
        return ja.retrieve_tags_trigger_to_json(self)


class SelectElement(Trigger):
    """Copy LIST_SPACE element pointed out by a position to WORK_SPACE.\n
       list_space=['a', 'b', 'c']\n
       SelectElement(position=0)\n
       work_space_after='a'\n
       SelectElement(position=2)\n
       work_space_after='c'
    """

    def __init__(self, position):
        super().__init__()
        self._position = int(position)

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.LIST:
            self._select_element(set_spaces)
            set_spaces.work_space = self.get_result()
            set_spaces.list_space = self.get_result_list()

    def _select_element(self, set_spaces):
        if len(set_spaces.list_space) >= self._position + 1:
            selection = set_spaces.list_space[self._position]
            self.set_result(selection)
            self.set_result_list(set_spaces.list_space)

    def to_string(self):
        return SelectElement.__name__ + '(position=' + str(self._position) + ')'

    def to_json(self):
        return ja.select_element_trigger_to_json(self)


class FetchAttribute(Trigger):
    """Searches for the first occurrence of an HTML tag attribute.\n
       FetchAttribute(name="id")\n
       work_space_before="<section class="glx-section" id="glx-library"><p>Text</p></section>"\n
       work_space_after="glx-library"
    """

    def __init__(self, name):
        super().__init__()
        self._name = name

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._fetch_attribute_value(set_spaces.web_space)
        elif target.set_name == rq.SpaceName.WORK:
            self._fetch_attribute_value(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _fetch_attribute_value(self, string):
        attribute_begin = str(self._name) + '="'
        attribute_end = '"'
        index_begin = str(string).find(attribute_begin)

        if index_begin == -1:
            logging.debug("Attribute not found; name=" + self._name)
            return

        offset = index_begin + len(attribute_begin)
        new_string = string[offset:]
        index_end = str(new_string).find(attribute_end)

        if index_end == -1:
            self.set_result(new_string)
            return

        self.set_result(new_string[0:index_end])

    def to_string(self):
        return FetchAttribute.__name__ + '(name=' + self._name + ')'

    def to_json(self):
        return ja.fetch_attribute_trigger_to_json(self)


class GetRegexMatch(Trigger):
    """It does a regex search within a single string and returns its group (the matched substring).\n
       regex="[\\d]+.[\\d]+.[\\d]+.[\\d]+"\n
       work_space_before="/galaxy_client_1.2.54.27.pkg"\n
       work_space_after="1.2.54.27"
       """

    def __init__(self, regex):
        super().__init__()
        self._regex = regex

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
            matched_substring = re.search(self._regex, text).group(0)
            self.set_result(matched_substring)
        except AttributeError:
            logging.debug("Regular expression group not found; regex=" + self._regex)

    def to_string(self):
        return GetRegexMatch.__name__ + '(regex=' + self._regex + ')'

    def to_json(self):
        return ja.get_regex_match_trigger_to_json(self)


class CutAside(Trigger):
    """Deletes some characters from string on both sides of it.\n
       CutAside(left=11, right=11)\n
       work_space_before="abcdefghijklmnopqrstuvwxyz"\n
       work_space_after="lmno"\n
       CutAside(left=0, right=4)\n
       work_space_before="catfish"\n
       work_space_after="cat"
    """

    def __init__(self, left, right):
        super().__init__()
        self._left = int(left)
        self._right = int(right)

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._cut_aside(set_spaces.web_space)
        elif target.set_name == rq.SpaceName.WORK:
            self._cut_aside(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _cut_aside(self, text):
        if self._left > len(str(text)):
            self.set_result(str())
            return

        left_cut = str(text)[self._left:]

        if self._right > len(str(left_cut)):
            self.set_result(str())
            return

        offset = len(left_cut) - self._right
        right_cut = str(left_cut)[0:offset]
        self.set_result(str(right_cut))

    def to_string(self):
        return CutAside.__name__ + '(left=' + str(self._left) + ', right=' + str(self._right) + ')'

    def to_json(self):
        return ja.cut_aside_trigger_to_json(self)


class SetWorkspace(Trigger):
    """Simply sets the work space.\n
       SetWorkspace(text="This is the work space now.")\n
       work_space_before="ABCDEFGHIJKLMNOPQRSTUVWXYZ"\n
       work_space_after="This is the work space now."\n
       """

    def __init__(self, text):
        super().__init__()
        self._text = text

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WORK:
            self.set_result(self._text)
            set_spaces.work_space = self.get_result()

    def to_string(self):
        return SetWorkspace.__name__ + '(text=' + self._text + ')'

    def to_json(self):
        return ja.set_workspace_trigger_to_json(self)


class GetSubset(Trigger):
    """Extracts a substring.\n
       GetSubset(begin=3, end=8)\n
       work_space_before="entanglement"\n
       work_space_after="angle"\n
       GetSubset(begin="float", end='d')\n
       work_space_before="The period can also occur in floating-point and imaginary literals."\n
       work_space_after="floating-point and"
    """

    def __init__(self, begin, end):
        super().__init__()
        self._begin = begin
        self._end = end

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WEB:
            self._get_subset(set_spaces.web_space)
        if target.set_name == rq.SpaceName.WORK:
            self._get_subset(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self.get_result()

    def _get_subset(self, text):
        if isinstance(self._begin, int) and isinstance(self._end, int):
            self._get_substring_integerify(text)
        elif isinstance(self._begin, str) and isinstance(self._end, str):
            self._get_substring_stringify(text)

    def _get_substring_integerify(self, text):
        start = int(self._begin)
        meta = int(self._end)

        if start > meta or meta > len(text):
            return ValueError('start > meta OR meta > len(text)')

        self.set_result(text[start:meta])

    def _get_substring_stringify(self, text):
        begin_index = str(text).find(self._begin)

        if begin_index == -1:
            self.set_result(str())
            return

        end_index = str(text[begin_index:]).find(self._end) + begin_index + len(self._end)

        if end_index == -1:
            self.set_result(str())
            return

        if begin_index > end_index:
            self.set_result(text[end_index:begin_index])
            return

        self.set_result(text[begin_index:end_index])

    def to_string(self):
        return GetSubset.__name__ + '(begin=' + str(self._begin) + ', end=' + str(self._end) + ')'

    def to_json(self):
        return ja.get_subset_trigger_to_json(self)


class AddText(Trigger):
    """Prepends and appends text to string.\n
       AddText(prepend="123_", append="_321")\n
       work_space_before="text"\n
       work_space_after="123_text_321"
    """

    def __init__(self, prepend, append):
        super().__init__()
        self._prepend_text = prepend
        self._append_text = append

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WORK:
            self.set_result(self._prepend_text + str(set_spaces.work_space) + self._append_text)
            set_spaces.work_space = self.get_result()

    def to_string(self):
        return AddText.__name__ + '(prepend=' + self._prepend_text + ', append=' + self._append_text + ')'

    def to_json(self):
        return ja.add_text_trigger_to_json(self)


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

    def __init__(self, string, characters):
        super().__init__()
        self._string = string
        self._characters = characters

    def invoke(self, target, set_spaces):
        if target.set_name == rq.SpaceName.WORK:
            self._delete_strings(set_spaces.work_space)
            self._delete_characters(self.get_result())
            set_spaces.work_space = self.get_result()

    def _delete_strings(self, source_str):
        if self._string == "":
            self.set_result(source_str)
            return

        target_str = str(source_str)

        while True:
            str_index = target_str.find(self._string)

            if str_index == -1:
                break

            end_index = str_index + len(self._string)
            target_str = target_str[:str_index] + target_str[end_index:]

        self.set_result(target_str)

    def _delete_characters(self, source_str):
        if self._characters == "":
            self.set_result(source_str)
            return

        target_str = str(source_str)

        for i in range(0, len(self._characters)):
            target_str = hp.remove_characters(target_str, self._characters[i])

        self.set_result(target_str)

    def to_string(self):
        return Delete.__name__ + '(string=' + self._string + ', characters=' + self._characters + ')'

    def to_json(self):
        return ja.delete_trigger_to_json(self)
