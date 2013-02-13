/* script.js */

var svgNS = "http://www.w3.org/2000/svg";
var vline, hline;
var svgdoc, canvas;

// var xdata = (1,2,3,4,5,6);
var ydata = [10,30,20,25,60,50];

var xp = [];
var yp = [];

var ymin=0;
var ymax=60;

var cWidth = 1024;
var cHeight = 768;

var heightScale = cHeight/ymax;

function init(evt){
  svgdoc = evt.target.ownerDocument;
  canvas = svgdoc.getElementById("canvas");
  
  vline = document.createElementNS(svgNS, "line");
  vline.setAttributeNS(null, "style", "stroke:black");
  canvas.appendChild(vline);
  
  hline = document.createElementNS(svgNS, "line");
  hline.setAttributeNS(null, "style", "stroke:black");
  canvas.appendChild(hline);
  
  drawGraph();
}

function drawGraph(){
  var i=1;
  for(i=1; i < ydata.length; i++){
  
    var step = cWidth/(ydata.length-1);
  
    var line = document.createElementNS(svgNS, "line");
    line.setAttributeNS(null, "style", "stroke:black");

    xp.push((i-1)*step);
    yp.push(yScale(ydata[i-1]));

    /*
    line.setAttributeNS(null, "x1", (i-1)*step);
    line.setAttributeNS(null, "x2", i*step);
    line.setAttributeNS(null, "y1", yScale(ydata[i-1]));
    line.setAttributeNS(null, "y2", yScale(ydata[i]));
    canvas.appendChild(line);
    */
    var line = new Line((i-1)*step, i*step, yScale(ydata[i-1]), yScale(ydata[i]));
    line.display(canvas);
  }

  xp.push(i*step);
  yp.push(yScale(ydata[i]));
}

function yScale(y){
  return cHeight-((y - ymin)*heightScale);
}

function mouseMoveBG(evt){
  var mX = evt.clientX;
  var mY = evt.clientY;
  drawCrosshairs(mX, mY);
  checkOverlap(mX, mY);
}


function drawCrosshairs(mX, mY){
  vline.setAttributeNS(null, "x1", mX);
  vline.setAttributeNS(null, "x2", mX);
  vline.setAttributeNS(null, "y1", 0);
  vline.setAttributeNS(null, "y2", cHeight);
  
  hline.setAttributeNS(null, "x1", 0);
  hline.setAttributeNS(null, "x2", cWidth);
  hline.setAttributeNS(null, "y1", mY);
  hline.setAttributeNS(null, "y2", mY);
}

function checkOverlap(x, y){
  var i=1;
  for(i=1; i < xp.length; i++){
      var m = (yp[i-1]-yp[i])/(xp[i-1]-xp[i]); // slope
      var b = yp[i] - (m*xp[i]);
  }
}

function Line(x1, x2, y1, y2)
{
    // endpoints
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;

    // y = mx + b
    this.m = (y2-y1)/(x2-x1);
    this.b = y1 - (this.m*x1);

    // DOM element
    this._line = document.createElementNS(svgNS, "line");
    this._line.setAttributeNS(null, "x1", x1);
    this._line.setAttributeNS(null, "x2", x2);
    this._line.setAttributeNS(null, "y1", y1);
    this._line.setAttributeNS(null, "y2", y2);
    // TODO support different styles
    this._line.setAttributeNS(null, "style", "stroke:black");

}

Line.prototype.display = function(canvas){
    canvas.appendChild(this._line);
}

