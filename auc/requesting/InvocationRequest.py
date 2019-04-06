from auc.structs.Target import Target
from auc.triggers.Trigger import Trigger


class InvocationRequest:
    def __init__(self, target, trigger):
        if not isinstance(target, Target) or not issubclass(trigger, Trigger):
            raise ValueError

        self.target = target
        self.trigger = trigger

    def to_string(self):
        return self.target.to_string() + ';' + self.trigger.to_string()
