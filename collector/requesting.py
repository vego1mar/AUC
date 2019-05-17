import json
import helpers as hp
import triggers as tr


class SetSpaces:
    def __init__(self):
        self.web_space = ""
        self.work_space = ""
        self.list_space = []


class SpaceName:
    WEB = 1,
    WORK = 2,
    LIST = 3


class SpaceNameHelper:
    STR_WEB = 'WEB'
    STR_WORK = 'WORK'
    STR_LIST = 'LIST'

    @staticmethod
    def get_set_name_str(obj):
        if str(obj) == str(SpaceName.WEB):
            return SpaceNameHelper.STR_WEB
        if str(obj) == str(SpaceName.WORK):
            return SpaceNameHelper.STR_WORK
        if str(obj) == str(SpaceName.LIST):
            return SpaceNameHelper.STR_LIST

    @staticmethod
    def get_set_name_obj(name):
        if str(name) == SpaceNameHelper.STR_WEB:
            return SpaceName.WEB
        if str(name) == SpaceNameHelper.STR_WORK:
            return SpaceName.WORK
        if str(name) == SpaceNameHelper.STR_LIST:
            return SpaceName.LIST


class Target(json.JSONEncoder):
    STR_SET_NAME = 'set_name'
    STR_IS_GATHERING_REQUEST = 'is_gathering_request'
    STR_COLLECTIBLE_NAME = 'collectible_name'

    def __init__(self, set_name, is_gathering_request=False, collectible_name="general"):
        super().__init__(indent=hp.get_json_indent())
        self.set_name = set_name
        self.is_gathering_request = is_gathering_request
        self.collectible_name = collectible_name

    def default(self, o):
        return self.to_dict()

    def to_dict(self):
        this = dict()
        this[Target.STR_SET_NAME] = SpaceNameHelper.get_set_name_str(self.set_name)
        this[Target.STR_IS_GATHERING_REQUEST] = self.is_gathering_request
        this[Target.STR_COLLECTIBLE_NAME] = self.collectible_name
        return this

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.JSONDecoder().decode(json_str)
        target = cls(**json_dict)
        target.set_name = SpaceNameHelper.get_set_name_obj(target.set_name)
        return target

    @classmethod
    def from_dict(cls, dct):
        set_name = SpaceNameHelper.get_set_name_obj(dct[Target.STR_SET_NAME])
        is_gathering_request = dct[Target.STR_IS_GATHERING_REQUEST]
        collectible_name = dct[Target.STR_COLLECTIBLE_NAME]
        return Target(set_name, is_gathering_request, collectible_name)

    def compare(self, obj):
        if not isinstance(obj, Target):
            return False

        result_1 = self.set_name is obj.set_name
        result_2 = self.is_gathering_request is obj.is_gathering_request
        result_3 = self.collectible_name == obj.collectible_name
        return result_1 and result_2 and result_3


class InvocationRequest(json.JSONEncoder):
    STR_TARGET = 'target'
    STR_TRIGGER = 'trigger'

    def __init__(self, target, trigger):
        super().__init__(indent=hp.get_json_indent())
        self.target = target
        self.trigger = trigger

    def default(self, o):
        return self.to_dict()

    def to_dict(self):
        this = dict()
        this[InvocationRequest.STR_TARGET] = self.target.to_dict()
        this[InvocationRequest.STR_TRIGGER] = self.trigger.to_dict()
        return this

    def to_json(self):
        return self.encode(self.to_dict())

    @classmethod
    def from_json(cls, json_str):
        json_dict = json.JSONDecoder().decode(json_str)
        request = cls(**json_dict)
        request.target = Target.from_dict(json_dict[InvocationRequest.STR_TARGET])
        request.trigger = tr.Trigger.get_obj(json_dict[InvocationRequest.STR_TRIGGER])
        return request

    @classmethod
    def from_dict(cls, dct):
        target = Target.from_dict(dct[InvocationRequest.STR_TARGET])
        trigger = tr.Trigger.get_obj(dct[InvocationRequest.STR_TRIGGER])
        return cls(target, trigger)

    def compare(self, obj):
        if not isinstance(obj, InvocationRequest):
            return False

        return self.target.compare(obj.target) and self.trigger.compare(obj.trigger)
