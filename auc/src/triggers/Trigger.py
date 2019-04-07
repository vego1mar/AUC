class Trigger:
    """This should be treated as an abstract class."""

    def __init__(self):
        self._result = ""

    def get_result(self):
        return self._result

    def invoke(self, target, set_spaces):
        raise NotImplementedError

    def to_string(self):
        raise NotImplementedError
