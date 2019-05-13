import unittest
import helpers as hp
import triggers as tr


class SetWorkspaceTriggerTestCase(unittest.TestCase):
    def test_json(self):
        # given
        trigger = tr.SetWorkspace(hp.decode_base64(b'0qTQlNCYRNCp0K/Qh9CT0IfQmEcg0IdTIFPQpNSg1JDQk9OJ0IfQmEc='))
        str = b'ewoJCSJ0ZXh0IjogIlx1MDRhNFx1MDQxNFx1MDQxOERcdTA0MjlcdTA0MmZcdTA0MDdcdTA0MTNcdTA0MDdcdTA0MThHIFx1MD' \
              b'QwN1MgU1x1MDQyNFx1MDUyMFx1MDUxMFx1MDQxM1x1MDRjOVx1MDQwN1x1MDQxOEciCn0='
        expected_json_str = hp.decode_base64(str)

        # when
        json_str = trigger.to_json()
        obj = tr.SetWorkspace.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertEqual(trigger.__dict__, obj.__dict__)


if __name__ == '__main__':
    unittest.main()
