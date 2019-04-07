from auc.src.triggers.Trigger import Trigger
from auc.src.structs.Target import Target
from auc.src.structs.Target import TargetSetName
from auc.src.structs.SetSpaces import SetSpaces


class FindTrigger(Trigger):
    """Finds the first occurrence of text_to_find and create a new string from that point to the end.
       Available for: WEB_SPACE
    """

    def __init__(self, text_to_find):
        super().__init__()
        self._text = str(text_to_find)

    def invoke(self, target, set_spaces):
        if not isinstance(set_spaces, SetSpaces) or not isinstance(target, Target):
            raise ValueError

        self._find_text(target, set_spaces)

    def _find_text(self, target, set_spaces):
        if target.set_name == TargetSetName.WEB_SPACE:
            self._find_text_in_webspace(set_spaces)
        else:
            raise ValueError

    def _find_text_in_webspace(self, set_spaces):
        text_index = str(set_spaces.web_space).find(self._text)

        if not text_index == -1:
            self._result = set_spaces.web_space[text_index:]

    def to_string(self):
        return str(type(FindTrigger)) + '(' + self._text + ')'
