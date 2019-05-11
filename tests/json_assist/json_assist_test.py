import unittest
import executing as ex
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

    def test_chain_request_to_json(self):
        # given
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("icon"))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.RetrieveTags("meta", tr.TagType.META, 2))
        req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.SelectElement(1))
        chain_request_1 = (req_1, req_2, req_3)
        chain_request_2 = [req_1, req_2, req_3]
        json_1 = b'ewoJInJlcXVlc3RfMSI6IHsKCQkidGFyZ2V0IjogewoJCQkic2V0X25hbWUiOiAiV0VCIiwKCQkJImlzX2dhdGhlcmluZ19y' \
                 b'ZXF1ZXN0IjogZmFsc2UsCgkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJfSwKCQkiZmluZF90cmlnZ2VyIjog' \
                 b'CXsKCQkJInRleHQiOiAiaWNvbiIKCQl9Cgl9LAoJInJlcXVlc3RfMiI6IHsKCQkidGFyZ2V0IjogewoJCQkic2V0X25hbWUi' \
                 b'OiAiV09SSyIsCgkJCSJpc19nYXRoZXJpbmdfcmVxdWVzdCI6IGZhbHNlLAoJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJnZW5l' \
                 b'cmFsIgoJCX0sCgkJInJldHJpZXZlX3RhZ3NfdHJpZ2dlciI6IAl7CgkJCSJuYW1lIjogIm1ldGEiLAoJCQkidHlwZSI6ICJN' \
                 b'RVRBIiwKCQkJImFtb3VudCI6IDIKCQl9Cgl9LAoJInJlcXVlc3RfMyI6IHsKCQkidGFyZ2V0IjogewoJCQkic2V0X25hbWUi' \
                 b'OiAiTElTVCIsCgkJCSJpc19nYXRoZXJpbmdfcmVxdWVzdCI6IGZhbHNlLAoJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJnZW5l' \
                 b'cmFsIgoJCX0sCgkJInNlbGVjdF9lbGVtZW50X3RyaWdnZXIiOiAJewoJCQkicG9zaXRpb24iOiAxCgkJfQoJfQp9Cg=='
        json_2 = b'CXsKCQkicmVxdWVzdF8xIjogewoJCQkidGFyZ2V0IjogewoJCQkJInNldF9uYW1lIjogIldFQiIsCgkJCQkiaXNfZ2F0aGVy' \
                 b'aW5nX3JlcXVlc3QiOiBmYWxzZSwKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJCX0sCgkJCSJmaW5kX3Ry' \
                 b'aWdnZXIiOiAJCXsKCQkJCSJ0ZXh0IjogImljb24iCgkJCX0KCQl9LAoJCSJyZXF1ZXN0XzIiOiB7CgkJCSJ0YXJnZXQiOiB7' \
                 b'CgkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZSwKCQkJCSJjb2xsZWN0' \
                 b'aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJCX0sCgkJCSJyZXRyaWV2ZV90YWdzX3RyaWdnZXIiOiAJCXsKCQkJCSJuYW1lIjog' \
                 b'Im1ldGEiLAoJCQkJInR5cGUiOiAiTUVUQSIsCgkJCQkiYW1vdW50IjogMgoJCQl9CgkJfSwKCQkicmVxdWVzdF8zIjogewoJ' \
                 b'CQkidGFyZ2V0IjogewoJCQkJInNldF9uYW1lIjogIkxJU1QiLAoJCQkJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogZmFsc2Us' \
                 b'CgkJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJnZW5lcmFsIgoJCQl9LAoJCQkic2VsZWN0X2VsZW1lbnRfdHJpZ2dlciI6IAkJ' \
                 b'ewoJCQkJInBvc2l0aW9uIjogMQoJCQl9CgkJfQoJfQo='
        expected_json_1 = hp.decode_base64(json_1)
        expected_json_2 = hp.decode_base64(json_2)

        # when
        json_str_1 = ja.chain_request_to_json(chain_request_1)
        json_str_2 = ja.chain_request_to_json(chain_request_2, 1)

        # then
        self.assertEqual(expected_json_1, json_str_1)
        self.assertEqual(expected_json_2, json_str_2)

    def test_execution_order_entry_to_json(self):
        # given
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WEB), tr.Find("find"))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK, True, "collectible"), tr.FetchAttribute("attr"))
        chain_request = (req_1, req_2)
        entry = ex.ExecutionOrderEntry(chain_request, "HTML or URL")
        json_1 = b'ewoJImNoYWluX3JlcXVlc3QiOiB7CgkJInJlcXVlc3RfMSI6IHsKCQkJInRhcmdldCI6IHsKCQkJCSJzZXRfbmFtZSI6ICJX' \
                 b'RUIiLAoJCQkJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogZmFsc2UsCgkJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJnZW5lcmFs' \
                 b'IgoJCQl9LAoJCQkiZmluZF90cmlnZ2VyIjogCQl7CgkJCQkidGV4dCI6ICJmaW5kIgoJCQl9CgkJfSwKCQkicmVxdWVzdF8y' \
                 b'IjogewoJCQkidGFyZ2V0IjogewoJCQkJInNldF9uYW1lIjogIldPUksiLAoJCQkJImlzX2dhdGhlcmluZ19yZXF1ZXN0Ijog' \
                 b'dHJ1ZSwKCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImNvbGxlY3RpYmxlIgoJCQl9LAoJCQkiZmV0Y2hfYXR0cmlidXRlX3Ry' \
                 b'aWdnZXIiOiAJCXsKCQkJCSJuYW1lIjogImF0dHIiCgkJCX0KCQl9Cgl9LAoJImh0bWxfZGF0YSI6ICJIVE1MIG9yIFVSTCIK' \
                 b'fQo='
        json_2 = b'CXsKCQkiY2hhaW5fcmVxdWVzdCI6IHsKCQkJInJlcXVlc3RfMSI6IHsKCQkJCSJ0YXJnZXQiOiB7CgkJCQkJInNldF9uYW1l' \
                 b'IjogIldFQiIsCgkJCQkJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogZmFsc2UsCgkJCQkJImNvbGxlY3RpYmxlX25hbWUiOiAi' \
                 b'Z2VuZXJhbCIKCQkJCX0sCgkJCQkiZmluZF90cmlnZ2VyIjogCQkJewoJCQkJCSJ0ZXh0IjogImZpbmQiCgkJCQl9CgkJCX0s' \
                 b'CgkJCSJyZXF1ZXN0XzIiOiB7CgkJCQkidGFyZ2V0IjogewoJCQkJCSJzZXRfbmFtZSI6ICJXT1JLIiwKCQkJCQkiaXNfZ2F0' \
                 b'aGVyaW5nX3JlcXVlc3QiOiB0cnVlLAoJCQkJCSJjb2xsZWN0aWJsZV9uYW1lIjogImNvbGxlY3RpYmxlIgoJCQkJfSwKCQkJ' \
                 b'CSJmZXRjaF9hdHRyaWJ1dGVfdHJpZ2dlciI6IAkJCXsKCQkJCQkibmFtZSI6ICJhdHRyIgoJCQkJfQoJCQl9CgkJfSwKCQki' \
                 b'aHRtbF9kYXRhIjogIkhUTUwgb3IgVVJMIgoJfQo='
        expected_json_1 = hp.decode_base64(json_1)
        expected_json_2 = hp.decode_base64(json_2)

        # when
        json_str_1 = ja.execution_order_entry_to_json(entry)
        json_str_2 = ja.execution_order_entry_to_json(entry, 1)

        # then
        self.assertEqual(expected_json_1, json_str_1)
        self.assertEqual(expected_json_2, json_str_2)

    def test_execution_order_to_json(self):
        # given
        req_1 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.Delete("ab", "b"))
        req_2 = rq.InvocationRequest(rq.Target(rq.SpaceName.LIST), tr.GetSubset(0, 1))
        entry_1 = ex.ExecutionOrderEntry((req_1, req_2), "html1")
        req_3 = rq.InvocationRequest(rq.Target(rq.SpaceName.WORK), tr.GetRegexMatch(r"^[\w]+;?$"))
        entry_2 = ex.ExecutionOrderEntry([req_3], "html2")
        execution_order = ex.ExecutionOrder()
        execution_order.add_entry(entry_1, True)
        execution_order.add_entry(entry_2, True)
        json_1 = b'ewoJImVudHJ5XzEiOiB7CgkJImNoYWluX3JlcXVlc3QiOiB7CgkJCSJyZXF1ZXN0XzEiOiB7CgkJCQkidGFyZ2V0IjogewoJ' \
                 b'CQkJCSJzZXRfbmFtZSI6ICJXT1JLIiwKCQkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZSwKCQkJCQkiY29sbGVj' \
                 b'dGlibGVfbmFtZSI6ICJnZW5lcmFsIgoJCQkJfSwKCQkJCSJkZWxldGVfdHJpZ2dlciI6IAkJCXsKCQkJCQkic3RyaW5nIjog' \
                 b'ImFiIiwKCQkJCQkiY2hhcmFjdGVycyI6ICJiIgoJCQkJfQoJCQl9LAoJCQkicmVxdWVzdF8yIjogewoJCQkJInRhcmdldCI6' \
                 b'IHsKCQkJCQkic2V0X25hbWUiOiAiTElTVCIsCgkJCQkJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogZmFsc2UsCgkJCQkJImNv' \
                 b'bGxlY3RpYmxlX25hbWUiOiAiZ2VuZXJhbCIKCQkJCX0sCgkJCQkiZ2V0X3N1YnNldF90cmlnZ2VyIjogCQkJewoJCQkJCQkJ' \
                 b'CQkJImJlZ2luIjogMCwKCQkJCQkJCQkJCSJlbmQiOiAxCgkJCQl9CgkJCX0KCQl9LAoJCSJodG1sX2RhdGEiOiAiaHRtbDEi' \
                 b'Cgl9LAoJImVudHJ5XzIiOiB7CgkJImNoYWluX3JlcXVlc3QiOiB7CgkJCSJyZXF1ZXN0XzEiOiB7CgkJCQkidGFyZ2V0Ijog' \
                 b'ewoJCQkJCSJzZXRfbmFtZSI6ICJXT1JLIiwKCQkJCQkiaXNfZ2F0aGVyaW5nX3JlcXVlc3QiOiBmYWxzZSwKCQkJCQkiY29s' \
                 b'bGVjdGlibGVfbmFtZSI6ICJnZW5lcmFsIgoJCQkJfSwKCQkJCSJnZXRfcmVnZXhfbWF0Y2hfdHJpZ2dlciI6IAkJCXsKCQkJ' \
                 b'CQkibmFtZSI6ICJeW1x3XSs7PyQiCgkJCQl9CgkJCX0KCQl9LAoJCSJodG1sX2RhdGEiOiAiaHRtbDIiCgl9Cn0K'
        json_2 = b'CXsKCQkiZW50cnlfMSI6IHsKCQkJImNoYWluX3JlcXVlc3QiOiB7CgkJCQkicmVxdWVzdF8xIjogewoJCQkJCSJ0YXJnZXQi' \
                 b'OiB7CgkJCQkJCSJzZXRfbmFtZSI6ICJXT1JLIiwKCQkJCQkJImlzX2dhdGhlcmluZ19yZXF1ZXN0IjogZmFsc2UsCgkJCQkJ' \
                 b'CSJjb2xsZWN0aWJsZV9uYW1lIjogImdlbmVyYWwiCgkJCQkJfSwKCQkJCQkiZGVsZXRlX3RyaWdnZXIiOiAJCQkJewoJCQkJ' \
                 b'CQkic3RyaW5nIjogImFiIiwKCQkJCQkJImNoYXJhY3RlcnMiOiAiYiIKCQkJCQl9CgkJCQl9LAoJCQkJInJlcXVlc3RfMiI6' \
                 b'IHsKCQkJCQkidGFyZ2V0IjogewoJCQkJCQkic2V0X25hbWUiOiAiTElTVCIsCgkJCQkJCSJpc19nYXRoZXJpbmdfcmVxdWVz' \
                 b'dCI6IGZhbHNlLAoJCQkJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJnZW5lcmFsIgoJCQkJCX0sCgkJCQkJImdldF9zdWJzZXRf' \
                 b'dHJpZ2dlciI6IAkJCQl7CgkJCQkJCQkJCQkJCSJiZWdpbiI6IDAsCgkJCQkJCQkJCQkJCSJlbmQiOiAxCgkJCQkJfQoJCQkJ' \
                 b'fQoJCQl9LAoJCQkiaHRtbF9kYXRhIjogImh0bWwxIgoJCX0sCgkJImVudHJ5XzIiOiB7CgkJCSJjaGFpbl9yZXF1ZXN0Ijog' \
                 b'ewoJCQkJInJlcXVlc3RfMSI6IHsKCQkJCQkidGFyZ2V0IjogewoJCQkJCQkic2V0X25hbWUiOiAiV09SSyIsCgkJCQkJCSJp' \
                 b'c19nYXRoZXJpbmdfcmVxdWVzdCI6IGZhbHNlLAoJCQkJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJnZW5lcmFsIgoJCQkJCX0s' \
                 b'CgkJCQkJImdldF9yZWdleF9tYXRjaF90cmlnZ2VyIjogCQkJCXsKCQkJCQkJIm5hbWUiOiAiXltcd10rOz8kIgoJCQkJCX0K' \
                 b'CQkJCX0KCQkJfSwKCQkJImh0bWxfZGF0YSI6ICJodG1sMiIKCQl9Cgl9Cg=='
        expected_json_1 = hp.decode_base64(json_1)
        expected_json_2 = hp.decode_base64(json_2)

        # when
        json_str_1 = ja.execution_order_to_json(execution_order)
        json_str_2 = ja.execution_order_to_json(execution_order, 1)

        # then
        self.assertEqual(expected_json_1, json_str_1)
        self.assertEqual(expected_json_2, json_str_2)

    def test_json_to_target(self):
        # given
        expected_target = rq.Target(rq.SpaceName.WORK, True, "variant_installer")
        str = b'CQkJInRhcmdldCI6IHsKCQkJCSJzZXRfbmFtZSI6ICJXT1JLIiwKCQkJCSJpc19nYXRoZXJpbmdfcmVxdWVzdCI6IHRydWUsC' \
              b'gkJCQkiY29sbGVjdGlibGVfbmFtZSI6ICJ2YXJpYW50X2luc3RhbGxlciIKCQkJfSw='
        target_str = hp.decode_base64(str)
        encoder = ja.JSONEncoder(target_str)

        # when
        target = encoder.json_to_target()

        # then
        self.assertIsInstance(target, rq.Target)
        self.assertEqual(expected_target.to_json(), target.to_json())

    def test_json_to_find_trigger(self):
        # given
        json_str = hp.decode_base64(b'CQkJImZpbmRfdHJpZ2dlciI6IAkJewoJCQkJInRleHQiOiAiaWQ9ImN0YSIiCgkJCX0=')
        expected_trigger = tr.Find(hp.decode_base64(b'aWQ9ImN0YSI='))
        encoder = ja.JSONEncoder(json_str)

        # when
        trigger = encoder.json_to_trigger()

        # then
        self.assertIsInstance(trigger, tr.Find)
        self.assertEqual(expected_trigger.to_json(), trigger.to_json())


if __name__ == '__main__':
    unittest.main()
