import unittest
import executing as ex
import helpers as hp
import requesting as rq
import triggers as tr


class ChainRequestExecutionTestData:
    WEB_PAGE_1_FILE_NAME = "../resources/lorem_ipsum.txt"

    def __init__(self):
        self._chain_request_1 = []
        self._web_page_1 = hp.fetch_file(self.WEB_PAGE_1_FILE_NAME)
        self._expected_collectibles = {}

    def get_web_page_1(self):
        return self._web_page_1

    def get_chain_request_1(self):
        request1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find(text="velit"))
        self._chain_request_1.append(request1)
        return self._chain_request_1

    def get_expected_collectibles_1(self):
        return self._expected_collectibles


class ChainRequestExecutionTest(unittest.TestCase):
    def test_execute_1(self):
        # given
        data = ChainRequestExecutionTestData()
        executor = ex.ChainRequestExecution(data.get_web_page_1())

        # when
        executor.set_chain_request(data.get_chain_request_1())
        executor.execute()

        # then
        self.assertEqual(executor.get_collectibles(), data.get_expected_collectibles_1())


if __name__ == '__main__':
    unittest.main()
