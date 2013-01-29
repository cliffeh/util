#!/usr/bin/perl

use Switch;

$top_margin=24;
$bottom_margin=24;
$left_margin=36;
$right_margin=0;

print '<?xml version="1.0" standalone="no"?>
<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN"
          "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">

<svg width="1024" height="768" version="1.1"
  xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
  <style type="text/css"><![CDATA[
    .hour {
      fill: none;
      stroke: none; /* black; */
      /* stroke-width: 0.25; */
      opacity: 0.5;
    }

    .sleep {
      fill: blue;
    }

    .raid {
      fill: green;
    }

    .work {
      fill: red;
    }

  ]]></style>

  <defs>
    <symbol id="hhRect">
      <rect x="0" y="0" height="15" width="141" />
    </symbol>
  </defs>

  <!-- daily width = 141 -->
  <!-- half-hour height = 15 -->

';

# "fudge factor" for aligning text with hour markers
# $fudge=5;
# for($i=0; $i < 48; $i++){
#     if($i%2==0){
#	print '  <text style="font-size:15px" x="2" y="'.($top_margin+$i*15+$fudge).'">00:00</text>'."\n";
#    }
# }

# time labels on the lefthand side
print '  <g id="time_labels">
    <text style="font-size:15px" x="2" y="29">00:00</text>
    <text style="font-size:15px" x="2" y="59">01:00</text>
    <text style="font-size:15px" x="2" y="89">02:00</text>
    <text style="font-size:15px" x="2" y="119">03:00</text>
    <text style="font-size:15px" x="2" y="149">04:00</text>
    <text style="font-size:15px" x="2" y="179">05:00</text>
    <text style="font-size:15px" x="2" y="209">06:00</text>
    <text style="font-size:15px" x="2" y="239">07:00</text>
    <text style="font-size:15px" x="2" y="269">08:00</text>
    <text style="font-size:15px" x="2" y="299">09:00</text>
    <text style="font-size:15px" x="2" y="329">10:00</text>
    <text style="font-size:15px" x="2" y="359">11:00</text>
    <text style="font-size:15px" x="2" y="389">12:00</text>
    <text style="font-size:15px" x="2" y="419">13:00</text>
    <text style="font-size:15px" x="2" y="449">14:00</text>
    <text style="font-size:15px" x="2" y="479">15:00</text>
    <text style="font-size:15px" x="2" y="509">16:00</text>
    <text style="font-size:15px" x="2" y="539">17:00</text>
    <text style="font-size:15px" x="2" y="569">18:00</text>
    <text style="font-size:15px" x="2" y="599">19:00</text>
    <text style="font-size:15px" x="2" y="629">20:00</text>
    <text style="font-size:15px" x="2" y="659">21:00</text>
    <text style="font-size:15px" x="2" y="689">22:00</text>
    <text style="font-size:15px" x="2" y="719">23:00</text>
  </g>

';
print '  <g id="day_labels">'."\n";
# day labels across the top
for($i=0; $i<7; $i++){
    switch($i){
	case 0 { $day = "Sunday"; }
	case 1 { $day = "Monday"; }
	case 2 { $day = "Tuesday"; }
	case 3 { $day = "Wednesday"; }
	case 4 { $day = "Thursday"; }
	case 5 { $day = "Friday"; }
	case 6 { $day = "Saturday"; }
    }
    print '    <text y="24" x="'.($i*141+40).'">'.$day.'</text>'."\n";
}
print '  </g>'."\n";

for($i = 0; $i < 7; $i++){
    $day = "";
    switch($i){
	case 0 { $day = "sunday"; }
	case 1 { $day = "monday"; }
	case 2 { $day = "tuesday"; }
	case 3 { $day = "wednesday"; }
	case 4 { $day = "thursday"; }
	case 5 { $day = "friday"; }
	case 6 { $day = "saturday"; }
    }
    $x=36+$i*141;
    print '  <g id="'.$day.'" transform="translate('.$x.' '.$top_margin.')">'."\n";
    for($j = 0; $j < 48; $j++){
	$y = $j*15;
	print '      <use xlink:href="#hhRect" class="hour" y="'.$y.'" />'."\n";
    }
    print '  </g>'."\n";
}
print '</svg>'."\n";
