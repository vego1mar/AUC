import unittest
import requesting as rq
import triggers as tr


class InvocationRequestTestCase(unittest.TestCase):
    def test_to_json_method(self):
        # given
        request = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.FetchAttribute("web"))
        expected = '{\n "target": {\n "set_name": "WEB"\n},\n "trigger": {\n "name": "web"\n}\n}'

        # when
        result = request.to_json()

        # then
        self.assertEqual(expected, result)


if __name__ == '__main__':
    unittest.main()
