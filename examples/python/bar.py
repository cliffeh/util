''' class & inheritance example '''

class Bar:
    def __init__(self, msg):
        assert isinstance(msg, str)
        self.msg = msg

    def emit(self):
        print self.msg

class Baz(Bar):
    def __init__(self, msg):
        Bar.__init__(self, "THIS IS A BAZ!:" + msg)
