import unittest
import helpers as hp
import requesting as rq
import triggers as tr


class RetrieveTagsConst:
    TEST_FILE_PATH = r"../resources/gog_galaxy_web_WinMac.base64"


class TestData1:
    def __init__(self):
        self.tag_name = "h1"
        self.tag_type = tr.TagType.SIMPLE
        self.amount = 1
        self.target = rq.Target(rq.SpaceName.WEB)
        self.set_spaces = rq.SetSpaces()
        self.set_spaces.web_space = hp.decode_base64(hp.fetch_file(RetrieveTagsConst.TEST_FILE_PATH).encode('ascii'))
        h1 = b'PGgxPgogICAgICAgIEdPRyBHQUxBWFkKICAgICAgICAgICAgICAgICAgICA8c3ZnIGNsYXNzPSJnbHgtaWNvbiBnbHgtYmlnLWhlY' \
             b'WRlcl9fbGFiZWwtaWNvbiI+PHVzZSB4bGluazpocmVmPSIjaWNvbi1yZWdpc3RlcmVkLXN5bWJvbCI+PC9zdmc+PC9oMT4='
        self.expected_result = hp.decode_base64(h1)


class TestData2:
    def __init__(self):
        self.tag_name = "g"
        self.tag_type = tr.TagType.ATTRIBUTED
        self.amount = 2
        self.target = rq.Target(rq.SpaceName.WEB)
        self.set_spaces = rq.SetSpaces()
        self.set_spaces.web_space = hp.decode_base64(hp.fetch_file(RetrieveTagsConst.TEST_FILE_PATH).encode('ascii'))
        g1 = b'PGcgaWQ9ImNhbWVyYS1feDMzXzVweCI+PHBhdGggZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiIGQ9Ik0zM' \
             b'C4yLDcuNWgtMy40Yy0wLjUsMC0wLjktMC4zLTEuMi0wLjdsLTItMy43Yy0wLjItMC40LTAuNy0wLjctMS4yLTAuN2gtOS45CgkJYy' \
             b'0wLjUsMC0wLjksMC4zLTEuMiwwLjdsLTIsMy43QzkuMiw3LjIsOC43LDcuNSw4LjMsNy41SDQuOGMtMi4zLDAtNC4zLDEuNS00LjM' \
             b'sNFYyOGMwLDIuNSwxLjksNC41LDQuMyw0LjVoMjUuNQoJCWMyLjMsMCw0LjItMiw0LjItNC41VjExLjVDMzQuNSw5LjEsMzIuNiw3' \
             b'LjUsMzAuMiw3LjV6IE0zMi41LDI5LjFjMCwwLjgtMC42LDEuNS0xLjQsMS41SDMuOWMtMC44LDAtMS40LTAuNy0xLjQtMS41VjExC' \
             b'gkJYzAtMC44LDAuNi0xLjUsMS40LTEuNWg1LjVjMC41LDAsMC45LTAuMywxLjItMC43bDItMy4zYzAuMi0wLjQsMC43LTAuOSwxLj' \
             b'ItMC45aDcuNmMwLjUsMCwwLjksMC40LDEuMiwwLjhsMS44LDMuNQoJCWMwLjIsMC40LDAuNywwLjcsMS4yLDAuN2g1LjdjMC44LDA' \
             b'sMS40LDAuNywxLjQsMS41VjI5LjF6IE0xNy41LDEzLjVjLTMuMywwLTYsMi43LTYsNmMwLDMuMywyLjcsNiw2LDZzNi0yLjcsNi02' \
             b'CgkJQzIzLjUsMTYuMiwyMC44LDEzLjUsMTcuNSwxMy41eiBNMTcuNSwyMy41Yy0yLjIsMC00LTEuOC00LTRjMC0yLjIsMS44LTQsN' \
             b'C00czQsMS44LDQsNEMyMS41LDIxLjcsMTkuNywyMy41LDE3LjUsMjMuNXoiLz48L2c+'
        g2 = b'PGcgaWQ9ImNoYXQtX3gzM181cHgiPjxwYXRoIGQ9Ik0zNC40LDIzLjlMMzIuMSwxOGMwLjctMS40LDEuNS0yLjksMS41LTQuNWMwL' \
             b'TYuMS00LjctMTEtMTEtMTFjLTQuOSwwLTguMywyLjYtOS45LDdDNi43LDkuOSwxLjQsMTUsMS40LDIwLjgKCQljMCwxLjYsMC45LD' \
             b'MuMSwxLjUsNC41bC0yLjQsNS45Yy0wLjEsMC4zLDAsMC45LDAuMiwxLjFjMC4yLDAuMiwwLjQsMC4zLDAuNiwwLjNjMC4xLDAsMC4' \
             b'yLDAsMC4zLTAuMWw1LjItMS42CgkJYzEuOSwxLjMsNC4yLDEuNyw2LjYsMS43YzUsMCw5LjMtMy44LDEwLjktOC4xYzIuMS0wLjEs' \
             b'MiwwLjIsMy44LTAuOWw1LjIsMS42YzAuMSwwLjEsMC4yLDAuMSwwLjMsMC4xYzAuMiwwLDAuNC0wLjEsMC42LTAuMwoJCUMzNC41L' \
             b'DI0LjcsMzQuNiwyNC4zLDM0LjQsMjMuOXogTTEzLjUsMzAuNmMtMi4yLDAtNC4zLTAuNC02LjEtMS43Yy0wLjEtMC4xLTAuMy0wLj' \
             b'EtMC40LTAuMWMtMC4xLDAtMC4yLDAtMC4zLDAuMUwzLDI5LjhsMS44LTQuMgoJCWMwLjEtMC4zLDAuMS0wLjYsMC0wLjhjLTAuNy0' \
             b'xLjMtMS4yLTIuNi0xLjItNGMwLTUsNC41LTkuMywxMC05LjNjNS41LDAsOSw0LjMsOSw5LjNDMjIuNSwyNS44LDE5LDMwLjYsMTMu' \
             b'NSwzMC42eiBNMjguNCwyMS41CgkJYy0wLjMtMC4xLTAuNS0wLjEtMC44LDAuMWMtMS40LDEtMSwwLjgtMi43LDFjMC4xLTAuNi0wL' \
             b'jQtMS4xLTAuNC0xLjdjMC01LjgtNC4xLTEwLjktMTAtMTEuM2MxLjYtMy4zLDQuMS00LjksOC4xLTQuOQoJCWM1LjUsMCw5LDQsOS' \
             b'w5YzAsMS40LTAuNywyLjctMS40LDRjLTAuMSwwLjMtMC4yLDAuNiwwLDAuOGwyLDQuMkwyOC40LDIxLjV6Ii8+PC9nPg=='
        self.expected_result_list = (hp.decode_base64(g1), hp.decode_base64(g2))


class TestData3:
    def __init__(self):
        self.tag_name = "link"
        self.tag_type = tr.TagType.META
        self.amount = 3
        self.target = rq.Target(rq.SpaceName.WEB)
        self.set_spaces = rq.SetSpaces()
        self.set_spaces.web_space = hp.decode_base64(hp.fetch_file(RetrieveTagsConst.TEST_FILE_PATH).encode('ascii'))
        link1 = b'PGxpbmsgcmVsPSJzaG9ydGN1dCBpY29uIiBocmVmPSIvZmF2aWNvbi5pY28/MyI+'
        link2 = b'PGxpbmsgcmVsPSJhcHBsZS10b3VjaC1pY29uIiBocmVmPSIvYXBwbGUtdG91Y2gtaWNvbi01N3g1Ny5wbmciIHNpemVzPSI1N3' \
                b'g1NyI+'
        link3 = b'PGxpbmsgcmVsPSJhcHBsZS10b3VjaC1pY29uIiBocmVmPSIvYXBwbGUtdG91Y2gtaWNvbi03Mng3Mi5wbmciIHNpemVzPSI3Mn' \
                b'g3MiI+'
        self.expected_result_list = (hp.decode_base64(link1), hp.decode_base64(link2), hp.decode_base64(link3))


class RetrieveTagsTriggerTest(unittest.TestCase):
    def test_invoke_1(self):
        # given
        dt = TestData1()
        trigger = tr.RetrieveTags(dt.tag_name, dt.tag_type, dt.amount)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result, trigger.get_result_list()[0])

    def test_invoke_2(self):
        # given
        dt = TestData2()
        trigger = tr.RetrieveTags(dt.tag_name, dt.tag_type, dt.amount)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result_list[0], str(trigger.get_result_list()[0]))
        self.assertEqual(dt.expected_result_list[1], str(trigger.get_result_list()[1]))

    def test_invoke_3(self):
        # given
        dt = TestData3()
        trigger = tr.RetrieveTags(dt.tag_name, dt.tag_type, dt.amount)

        # when
        trigger.invoke(dt.target, dt.set_spaces)

        # then
        self.assertEqual(dt.expected_result_list[0], str(trigger.get_result_list()[0]))
        self.assertEqual(dt.expected_result_list[1], str(trigger.get_result_list()[1]))
        self.assertEqual(dt.expected_result_list[2], str(trigger.get_result_list()[2]))

    def test_json(self):
        # given
        trigger = tr.RetrieveTags('href', tr.TagType.META, 101)
        json_str = b'ewoJCSJ0YWdfbmFtZSI6ICJocmVmIiwKCQkidGFnX3R5cGUiOiAiTUVUQSIsCgkJImFtb3VudCI6IDEwMQp9'
        expected_json_str = hp.decode_base64(json_str)

        # when
        json_str = trigger.to_json()
        obj = tr.RetrieveTags.from_json(json_str)

        # then
        self.assertEqual(expected_json_str, json_str)
        self.assertEqual(trigger.__dict__, obj.__dict__)


if __name__ == '__main__':
    unittest.main()
