MODE 1: Find Word
Corresponding to the input Word, get all the key list which occurred more than N times. (the default N is 3)
	$java Decryptor <CipherFileName> 1 "<Word>"
	$java Decryptor <CipherFileName> 1 "<Word>" <N>
	eg. $java Decryptor ciphertext1 1 " th" 5

MODE 2: Decipher
	$java Decryptor <CipherFileName> 2 “<Ketlist>“
	eg. $java Decryptor ciphertext1 2 "{80, 47, 8, 124, 95, 48, 0}"
	key for ciphertext1 = P,/,BS,|,_,0,NUL
