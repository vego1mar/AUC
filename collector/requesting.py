import helpers as hp
import json_assist as ja


class SetSpaces:
    def __init__(self):
        self.web_space = ""
        self.work_space = ""
        self.list_space = []


class SpaceName:
    WEB = 1,
    WORK = 2,
    LIST = 3


class Target:
    def __init__(self, set_name, is_gathering_request=False, collectible_name="general"):
        self.set_name = set_name
        self.is_gathering_request = is_gathering_request
        self.collectible_name = collectible_name

    def to_string(self):
        if not self.is_gathering_request:
            return Target.__name__ + '(set_name=' + hp.get_set_name_str(self.set_name) + ')'

        return Target.__name__ + '(set_name=' + hp.get_set_name_str(self.set_name) + ', is_gathering_request=' + \
               str(self.is_gathering_request) + ', collectible_name=' + self.collectible_name + ')'

    def to_json(self):
        return ja.target_to_json(self)


class InvocationRequest:
    def __init__(self, target, trigger):
        self.target = target
        self.trigger = trigger

    def to_string(self):
        return '[' + self.target.to_string() + ', ' + self.trigger.to_string() + ']'

    def to_json(self):
        return ja.invocation_request_to_json(self)
