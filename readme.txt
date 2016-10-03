Part 1
	MODE 1: Find Word
	Corresponding to the input Word, get all the key list which occurred more than N 	times. (the default N is 3)
		$java Decryptor <CipherFileName> 1 "<Word>"
		$java Decryptor <CipherFileName> 1 "<Word>" <N>
		eg. $java Decryptor ciphertext1 1 " th" 5

	MODE 2: Decipher
		$java Decryptor <CipherFileName> 2 “<Ketlist>“
		eg. $java Decryptor ciphertext1 2 "{80, 47, 8, 124, 95, 48, 0}"
		key for ciphertext1 = P,/,BS,|,_,0,NUL

Part 2
	7e 74 2c 01 IMG	 	Reportedly a proprietary recording system, possibly a 	Digital Watchdog DW-TP-500G unit.



	FF D8 FF e1 JPEG EXIF
	5  3  .  5  {   .  5  6  3  N  ,  -
	53 51 46 53 123 46 53 54 51 78 44 45
	5  3  .  5  {   .  %  S  5  @  ,  -
	53 51 46 53 123 46 37 83 53 64 44 45


	JPG have trailer ff d9 so key ends with , -

	workspace all values in hex -- known || pretty sure ?? variable using lowest 	plausible value
ff d8 ff e1 02 00 45 78 69 66 00 00 4d 4d 00 2a 00 00 00 08  00 02  01 0e 00 02 00 00 00 00  00 00 00 1a 00 00 00 00 00 00 00 00  00 00 00 00
-- -- -- || ?? ?? || || || || || || -- -- -- --   ????????    ????   ????  ????   ????????     ????????   ????  ????  ????????      ||||||||
SOI   EXIF  ascii 'E''x''i''f'?? ?? tiff            >= 8       >0   descr  ascii              offsetValue                        if >0 bytes==2
    SPIFF=8 NUL                     MMorder         even     4 6 7                               26.d                              makes it 0
                                                             9 b 12  \__________TIFF Tag 1_____________/  \__________TIFF Tag 2_____________/

5  3  .  5  [  %  5  6  3  N  ,  -  1  1  3  .  5  2  8  9  d  W  E 3  .

