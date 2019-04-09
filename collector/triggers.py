import logging
from .requesting import TargetSetName
from .helpers import get_tag_type_name


class Trigger:
    """This should be treated as an abstract class."""

    def __init__(self):
        self._result = None

    def get_result(self):
        return self._result

    def set_result(self, result):
        self._result = result

    def invoke(self, target, set_spaces):
        raise NotImplementedError

    def to_string(self):
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
        if target.set_name == TargetSetName.WEB_SPACE:
            self._find_text_in_string(set_spaces.web_space)
        elif target.set_name == TargetSetName.WORK_SPACE:
            self._find_text_in_string(set_spaces.work_space)
        else:
            return

        set_spaces.work_space = self._result

    def _find_text_in_string(self, string_to_search_in):
        text_index = str(string_to_search_in).find(self._text)

        if not text_index == -1:
            self._result = string_to_search_in[text_index:]
        else:
            logging.debug("Text not found; text=" + self._text)

    def to_string(self):
        return Find.__name__ + '(name=' + self._text + ')'


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
        if target.set_name == TargetSetName.WEB_SPACE:
            self._find_next_text(set_spaces.web_space)
        elif target.set_name == TargetSetName.WORK_SPACE:
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
        if target.set_name == TargetSetName.WEB_SPACE:
            self._retrieve_tags_from(set_spaces.web_space)
        elif target.set_name == TargetSetName.WORK_SPACE:
            self._retrieve_tags_from(set_spaces.work_space)
        else:
            return

        set_spaces.list_space = self.get_result()

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

        self.set_result(tags_list)

    def to_string(self):
        return RetrieveTags.__name__ + '(name=' + self._name + ', type=' + get_tag_type_name(self._type) \
               + ', amount=' + str(self._amount) + ')'


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
        if target.set_name == TargetSetName.LIST_SPACE:
            self._select_element(set_spaces)
            set_spaces.work_space = self.get_result()

    def _select_element(self, set_spaces):
        if len(set_spaces.list_space) >= self._position + 1:
            selection = set_spaces.list_space[self._position]
            self.set_result(selection)

    def to_string(self):
        return SelectElement.__name__ + '(position=' + str(self._position) + ')'
