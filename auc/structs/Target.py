class TargetSetName:
    WEB_SPACE = 1,
    WORK_SPACE = 2,
    LIST_SPACE = 3


class Target:
    def __init__(self, set_name, is_gathering_request=False, collectible_name="general"):
        self.set_name = set_name
        self.is_gathering_request = is_gathering_request
        self.collectible_name = collectible_name

    def to_string(self):
        if not self.is_gathering_request:
            return '{' + self.set_name + ',' + str(self.is_gathering_request) + '}'

        return '{' + self.set_name + ',' + str(self.is_gathering_request) + ',' + self.collectible_name + '}'
