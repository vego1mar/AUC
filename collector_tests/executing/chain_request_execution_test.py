import unittest
from collector.requesting import InvocationRequest
from collector.requesting import Target
from collector.requesting import TargetSetName
from collector.triggers import Find
from collector.executing import ChainRequestExecution
from collector.helpers import fetch_file


class ChainRequestExecutionTestData():
    def __init__(self):
        self._chain_request_1 = []
        self._WEB_PAGE_1_FILE_NAME = "../resources/lorem_ipsum.txt"
        self._web_page_1 = fetch_file(self._WEB_PAGE_1_FILE_NAME)
        self._expected_collectibles = {}

    def get_web_page_1(self):
        return self._web_page_1

    def get_chain_request_1(self):
        request1 = InvocationRequest(Target(TargetSetName.WEB_SPACE), Find(text="velit"))
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
