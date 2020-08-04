# Builder Demo in Python

class SimpleHtmlBuilder(object):
    def __init__(self):
        self.bodyContent = ''
    
    # Basic methods:
    def AddH1(self, text):
        self.bodyContent += '<h1>' + text + '</h1>'
    
    def AddH2(self, text):
        self.bodyContent += '<h2>' + text + '</h2>'

    def AddParagraph(self, text):
        self.bodyContent += '<p>' + text + '</p>'

    def AddHorizontalRule(self):
        self.bodyContent += '<hr>'

    def AddLineBreak(self):
        self.bodyContent += '<br>'

    # Builds the html document based on our input:
    def BuildDocument(self):
        # We'll follow the HTML 5 standard document syntax:
        return '''
        <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    {}
</body>
</html>'''.format(self.bodyContent)



# Demo of using our simple html builder:

htmlBuilder = SimpleHtmlBuilder()

# Create the content of the page:
htmlBuilder.AddH1("Welcome to my blog!")
htmlBuilder.AddHorizontalRule()
htmlBuilder.AddH2("What is my blog about?")
htmlBuilder.AddParagraph("Good question! I'm not so sure yet, but you can't rush creativity.")
htmlBuilder.AddLineBreak()
htmlBuilder.AddHorizontalRule()

# Build the html:
print(htmlBuilder.BuildDocument())
