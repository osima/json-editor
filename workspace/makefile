

all :
	cd ..;tar cf tmp.tar `find src -name "*groovy"`
	tar xf ../tmp.tar
	$(RM) ../tmp.tar
	cp ../build.gradle .
	cp -rf ../resources .
	gradle

clean :
	gradle clean;
	$(RM) build.gradle
	$(RM) -rf src
	$(RM) -rf resources
	$(RM) ../tmp.tar

