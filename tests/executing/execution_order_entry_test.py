import unittest
import executing as ex
import requesting as rq
import triggers as tr


def get_chain_request():
    req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.FindNext("next"))
    req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.SetWorkspace("space"))
    req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(0))
    return req_1, req_2, req_3


class ExecutionOrderEntryTestCase(unittest.TestCase):
    def test_json_encode(self):
        # given
        entry = ex.ExecutionOrderEntry(get_chain_request(), str())
        expected = '{\n\n}'

        # when
        result = entry.to_json()

        # then
        self.assertEqual(expected, result)


if __name__ == '__main__':
    unittest.main()
