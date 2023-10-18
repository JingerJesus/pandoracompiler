subroutine count[uint in];
	uint out = in ++;
	return out;
end;

uint counter = %0;
   :flagA
jumpif flagB counter == %10;
counter = count[counter];
jump flagA;
:flagB
halt;