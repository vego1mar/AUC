import unittest
import helpers as hp
import json_assist as ja
import requesting as rq
import triggers as tr


class JSONAssistTestCase(unittest.TestCase):
    def test_target_to_json(self):
        # given
        target = rq.Target(rq.SpaceName.WORK, True, "empty")
        expected = b'CXsKCQkic2V0X25hbWUiOiAiV09SSyIsCgkJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogdHJ1ZSwKCQkiY29sbGVjdGlibG' \
                   b'VfbmFtZSI6ICJlbXB0eSIKCX0K'
        expected_json = hp.decode_base64(expected)

        # when
        json_str = ja.target_to_json(target, prepend_indent_no=1)

        # then
        self.assertEqual(expected_json, json_str)

    def test_find_trigger_to_json(self):
        # given
        trigger = tr.Find("dummy_text")
        expected_json = hp.decode_base64(b'CQl7CgkJCSJ0ZXh0IjogImR1bW15X3RleHQiCgkJfQo=')

        # when
        json_str = ja.find_trigger_to_json(trigger, prepend_indent_no=2)

        # then
        self.assertEqual(expected_json, json_str)

    def test_find_next_trigger_to_json(self):
        # given
        trigger = tr.FindNext("dummy_text")
        expected_json = hp.decode_base64(b'CXsKCQkidGV4dCI6ICJkdW1teV90ZXh0IgoJfQo=')

        # when
        json_str = ja.find_next_trigger_to_json(trigger, prepend_indent_no=1)

        # then
        self.assertEqual(expected_json, json_str)

    def test_retrieve_tags_trigger_to_json(self):
        # given
        trigger = tr.RetrieveTags("h", tr.TagType.SIMPLE, 0)
        expected_json = hp.decode_base64(b'ewoJIm5hbWUiOiAiaCIsCgkidHlwZSI6ICJTSU1QTEUiLAoJImFtb3VudCI6IDAKfQo=')

        # when
        json_str = ja.retrieve_tags_trigger_to_json(trigger)

        # then
        self.assertEqual(expected_json, json_str)

    def test_select_element_trigger_to_json(self):
        # given
        trigger = tr.SelectElement(1324)
        expected_json = hp.decode_base64(b'ewoJInBvc2l0aW9uIjogMTMyNAp9Cg==')

        # when
        json_str = ja.select_element_trigger_to_json(trigger)

        # then
        self.assertEqual(expected_json, json_str)

    def test_fetch_attribute_trigger_to_json(self):
        # given
        trigger = tr.FetchAttribute("href")
        expected_json = hp.decode_base64(b'CXsKCQkibmFtZSI6ICJocmVmIgoJfQo=')

        # when
        json_str = ja.fetch_attribute_trigger_to_json(trigger, 1)

        # then
        self.assertEqual(expected_json, json_str)

    def test_get_regex_match_trigger_to_json(self):
        # given
        trigger = tr.GetRegexMatch(r"^[\d]+[.,]+[\d]*?$")
        expected_json = hp.decode_base64(b'ewoJIm5hbWUiOiAiXltcZF0rWy4sXStbXGRdKj8kIgp9Cg==')

        # when
        json_str = ja.get_regex_match_trigger_to_json(trigger)

        # then
        self.assertEqual(expected_json, json_str)

    def test_cut_aside_trigger_to_json(self):
        # given
        trigger = tr.CutAside(1, 2)
        expected_json = hp.decode_base64(b'ewoJImxlZnQiOiAxLAoJInJpZ2h0IjogMgp9Cg==')

        # when
        json_str = ja.cut_aside_trigger_to_json(trigger)

        # then
        self.assertEqual(expected_json, json_str)

    def test_set_workspace_trigger_to_json(self):
        # given
        trigger = tr.SetWorkspace("ewoJImxlZnQiOiAxLAoJInJpZ2h0IjogMgp9Cg")
        expected_json = hp.decode_base64(b'ewoJInRleHQiOiAiZXdvSklteGxablFpT2lBeExBb0pJbkpwWjJoMElqb2dNZ3A5Q2ciCn0K')

        # when
        json_str = ja.set_workspace_trigger_to_json(trigger)

        # then
        self.assertEqual(expected_json, json_str)

    def test_get_subset_trigger_to_json(self):
        # given
        trigger_1 = tr.GetSubset(0, 1)
        trigger_2 = tr.GetSubset("0", "1")
        expected_json_1 = hp.decode_base64(b'ewoJCSJiZWdpbiI6IDAsCgkJImVuZCI6IDEKfQo=')
        expected_json_2 = hp.decode_base64(b'CQkJewoJCQkJCQkJCSJiZWdpbiI6ICIwIiwKCQkJCQkJCQkiZW5kIjogIjEiCgkJCX0K')

        # when
        json_str_1 = ja.get_subset_trigger_to_json(trigger_1)
        json_str_2 = ja.get_subset_trigger_to_json(trigger_2, 3)

        # then
        self.assertEqual(expected_json_1, json_str_1)
        self.assertEqual(expected_json_2, json_str_2)

    def test_add_text_trigger_to_json(self):
        # given
        trigger = tr.AddText("abc", "xyz")
        expected_json = hp.decode_base64(b'ewoJInByZXBlbmRfdGV4dCI6ICJhYmMiLAoJImFwcGVuZF90ZXh0IjogInh5eiIKfQo=')

        # when
        json_str = ja.add_text_trigger_to_json(trigger)

        # then
        self.assertEqual(expected_json, json_str)

    def test_delete_trigger_to_json(self):
        # given
        trigger = tr.Delete("abc123", "xyz987")
        expected_json = hp.decode_base64(b'ewoJInN0cmluZyI6ICJhYmMxMjMiLAoJImNoYXJhY3RlcnMiOiAieHl6OTg3Igp9Cg==')

        # when
        json_str = ja.delete_trigger_to_json(trigger)

        # then
        self.assertEqual(expected_json, json_str)

    def test_invocation_request_to_json(self):
        # given
        request = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.FetchAttribute("sth"))
        exp_1 = b'ewoJInRhcmdldCI6IHsKCQkic2V0X25hbWUiOiAiV09SSyIsCgkJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogZmFsc2UsCgkJ' \
                b'ImNvbGxlY3RpYmxlX25hbWUiOiAiZ2VuZXJhbCIKCX0sCgkiZmV0Y2hfYXR0cmlidXRlX3RyaWdnZXIiOiB7CgkJIm5hbWUi' \
                b'OiAic3RoIgoJfQp9Cg=='
        exp_2 = b'CXsKCQkidGFyZ2V0IjogewoJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCSJpc19nYXRoZXJpbmdfcmVxdWVzdCI6IGZhbHNl' \
                b'LAoJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJnZW5lcmFsIgoJCX0sCgkJImZldGNoX2F0dHJpYnV0ZV90cmlnZ2VyIjogCXsK' \
                b'CQkJIm5hbWUiOiAic3RoIgoJCX0KCX0K'
        expected_str_1 = hp.decode_base64(exp_1)
        expected_str_2 = hp.decode_base64(exp_2)

        # when
        json_str_1 = ja.invocation_request_to_json(request, 0)
        json_str_2 = ja.invocation_request_to_json(request, 1)

        # then
        self.assertEqual(expected_str_1, json_str_1)
        self.assertEqual(expected_str_2, json_str_2)


if __name__ == '__main__':
    unittest.main()
