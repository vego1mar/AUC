import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class DeleteTestData:
    def __init__(self):
        self.string = str()
        self.characters = str()
        self.target = rq.Target(rq.SpaceName.WORK)
        self.set_spaces = rq.SetSpaces()
        self.expected_result = str()


class DeleteTriggerTest(unittest.TestCase):
    def test_strings_deleting(self):
        # given
        dt = DeleteTestData()
        dt.string = hp.decode_base64(b'ZmV1Z2lhdCBwcmV0aXVtIG5pYmggaXA=')
        work_space = b'Q3JhcyBmZXJtZW50dW0gb2RpbyBldSBmZXVnaWF0IHByZXRpdW0gbmliaCBpcHN1bSBjb25zZXF1YXQu'
        dt.set_spaces.work_space = hp.decode_base64(work_space)
        dt.expected_result = hp.decode_base64(b'Q3JhcyBmZXJtZW50dW0gb2RpbyBldSBzdW0gY29uc2VxdWF0Lg==')
        trigger = tr.Delete(dt.string, dt.characters)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_characters_deleting(self):
        # given
        dt = DeleteTestData()
        dt.characters = hp.decode_base64(b'YWJlcnR2IHouaQ==')
        work_space = b'TmVjIHNhZ2l0dGlzIGFsaXF1YW0gbWFsZXN1YWRhIGJpYmVuZHVtIGFyY3Ugdml0YWUgZWxlbWVudHVtIGN1cmFiaXR' \
                     b'1ciB2aXRhZS4g'
        dt.set_spaces.work_space = hp.decode_base64(work_space)
        dt.expected_result = hp.decode_base64(b'TmNzZ3NscXVtbWxzdWRuZHVtY3VsbW51bWN1dQ==')
        trigger = tr.Delete(dt.string, dt.characters)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_string_and_characters_deleting(self):
        # given
        dt = DeleteTestData()
        dt.string = hp.decode_base64(b'bm9uIA==')
        dt.characters = hp.decode_base64(b'dml0YWUgdW8=')
        work_space = b'U2NlbGVyaXNxdWUgaW4gZGljdHVtIG5vbiBjb25zZWN0ZXR1ciBhIGVyYXQgbmFtIGF0IGxlY3R1cy4gVXQgYWxpcXV' \
                     b'hbSBwdXJ1cyBzaXQgYW1ldCBsdWN0dXMgdmVuZW5hdGlzLiBMYWN1cyBsYW9yZWV0IG5vbiBjdXJhYml0dXIgZ3Jhdm' \
                     b'lkYSBhcmN1IGFjIHRvcnRvci4gQ29uc2VxdWF0IG5pc2wgdmVsIHByZXRpdW0gbGVjdHVzIHF1YW0gaWQgbGVvLiBWa' \
                     b'XRhZSB0b3J0b3IgY29uZGltZW50dW0gbGFjaW5pYSBxdWlzLg=='
        dt.set_spaces.work_space = hp.decode_base64(work_space)
        result = b'U2NscnNxbmRjbWNuc2Nycm5tbGNzLlVscW1wcnNzbWxjc25ucy5MY3NscmNyYnJncmRyY2Nyci5DbnNxbnNsbHBybWxjc3F' \
                 b'tZGwuVnJyY25kbW5tbGNucXMu'
        dt.expected_result = hp.decode_base64(result)
        trigger = tr.Delete(dt.string, dt.characters)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result())

    def test_json(self):
        # given
        trigger = tr.Delete(hp.decode_base64(b'YWJj'), hp.decode_base64(b'Yw=='))
        jstr = b'ewoJCSJ0cmlnZ2VyX3R5cGUiOiAiZGVsZXRlX3RyaWdnZXIiLAoJCSJzdHJpbmciOiAiYWJjIiwKCQkiY2hhcmFjdGVycyI6IC' \
               b'JjIgp9'
        expected_json_str = hp.decode_base64(jstr)

        # when
        json_str = trigger.to_json()
        obj = tr.Delete.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertTrue(trigger.compare(obj), obj.compare(obj))


if __name__ == '__main__':
    unittest.main()
