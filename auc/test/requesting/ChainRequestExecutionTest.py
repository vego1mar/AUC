import unittest
from auc.src.requesting.ChainRequestExecution import ChainRequestExecution
from auc.src.requesting.InvocationRequest import InvocationRequest
from auc.src.structs.Target import Target
from auc.src.structs.Target import TargetSetName
from auc.src.triggers.FindTrigger import FindTrigger


class ChainRequestExecutionTestData():
    def __init__(self):
        self._chain_request_1 = []
        self._WEB_PAGE_1_FILE_NAME = "../_resources/lorem_ipsum.txt"
        self._web_page_1 = self._load_web_page(self._WEB_PAGE_1_FILE_NAME)
        self._expected_collectibles = {}

    def _load_web_page(self, file_name):
        with open(file_name) as file:
            return str(file.readlines())

    def get_web_page_1(self):
        return self._web_page_1

    def get_chain_request_1(self):
        request1 = InvocationRequest(Target(TargetSetName.WEB_SPACE), FindTrigger(text_to_find="velit"))
        self._chain_request_1.append(request1)
        return self._chain_request_1

    def get_expected_collectibles_1(self):
        return self._expected_collectibles


class ChainRequestExecutionTest(unittest.TestCase):
    def test_execute_1(self):
        # given
        data = ChainRequestExecutionTestData()
        executor = ChainRequestExecution(data.get_web_page_1())

        # when
        executor.set_chain_request(data.get_chain_request_1())
        executor.execute()

        # then
        self.assertEqual(executor.get_collectibles(), data.get_expected_collectibles_1())


if __name__ == '__main__':
    unittest.main()
