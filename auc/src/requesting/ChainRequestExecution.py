from auc.src.structs.SetSpaces import SetSpaces
from auc.src.structs.Target import TargetSetName


class ChainRequestExecution:
    def __init__(self, web_page):
        self._set_spaces = SetSpaces()
        self._set_spaces.web_space = web_page
        self._chain_request = []
        self._collectibles = {}

    def set_chain_request(self, chain_request):
        if isinstance(chain_request, type([])):
            self._chain_request = chain_request

    def get_collectibles(self):
        return self._collectibles

    def execute(self):
        for request in self._chain_request:
            target = request.target
            trigger = request.trigger
            trigger.invoke(target, self._set_spaces)
            self._alter_target_space(target, trigger)
            self._acquire_collectible(target)

    def _alter_target_space(self, target, trigger):
        if target.set_name == TargetSetName.WEB_SPACE:
            self._set_spaces.work_space = trigger.get_result()
        elif target.set_name == TargetSetName.LIST_SPACE:
            raise NotImplementedError
        else:
            raise ValueError("Not supported set space.")

    def _acquire_collectible(self, target):
        if target.is_gathering_request:
            self._collectibles[target.collectible_name] = self._set_spaces.work_space
