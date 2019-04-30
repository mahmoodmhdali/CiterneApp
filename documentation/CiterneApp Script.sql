CREATE DATABASE  IF NOT EXISTS `db_citerne_app` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_citerne_app`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_citerne_app
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_event_class`
--

DROP TABLE IF EXISTS `tbl_event_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class` (
  `ID` bigint(20) NOT NULL,
  `TITLE` varchar(61) NOT NULL,
  `CATEGORY` bigint(20) NOT NULL,
  `TYPE` bigint(20) NOT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `PUBLISHED` tinyint(4) NOT NULL DEFAULT '1',
  `DURATION` int(10) DEFAULT NULL,
  `COUNTRY` bigint(20) NOT NULL,
  `TICKETING_URL` varchar(201) CHARACTER SET latin1 DEFAULT NULL,
  `ABOUT` longtext NOT NULL,
  `EVENT_INDEX` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_7256738265782384787_idx` (`CATEGORY`),
  KEY `FK_78572893572785236453_idx` (`TYPE`),
  KEY `FK_847568276378546123764_idx` (`COUNTRY`),
  CONSTRAINT `FK_7256738265782384787` FOREIGN KEY (`CATEGORY`) REFERENCES `tbl_event_class_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_7365427623747236742` FOREIGN KEY (`COUNTRY`) REFERENCES `tbl_event_class_country` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_78572893572785236453` FOREIGN KEY (`TYPE`) REFERENCES `tbl_event_class_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class`
--

LOCK TABLES `tbl_event_class` WRITE;
/*!40000 ALTER TABLE `tbl_event_class` DISABLE KEYS */;
INSERT INTO `tbl_event_class` VALUES (1,'Minaret',1,1,'2019-04-01 16:20:00',NULL,NULL,1,60,1,'https://www.antoineticketing.com/index.php?event_id=7248','“For nearly 1,000 years the minaret of the Great Mosque of Aleppo soared\nabove Syria\'s largest city. It now lies in ruins. The once existing \'monument\'\nrepresented the city\'s soul. A monumental portrayal of the cultural, the social,\nthe historical and the religious, it stood witness to countless generations of\ngatherings. With an eclectic range of artists, #minaret proposes a core\nquestion of our everyday life. How do we position ourselves, resist ‘numbing’,\nand face the images of destruction and confiscation of entire cities and the\ndaily violation of human intimacy and privacy?”\nAfter it’s world premiere at the 2018 Romaeuropa Festival, Omar Rajeh |\nMaqamat is thrilled to present #minaret for the opening of it’s new\nperformance space, Citerne Beirut. Later on this year, #minaret will have its\ninternational tour starting this summer at the Athens Festival, Julidans,\nBoulevard Festival, the Moscow Dance Inversion International Contemporary\nFestival, followed by SPRING Performing Arts Festival in Utrecht.\n#ميدنه\nعلى مدى قرابة ألف سنة، ارتفعت مئذنة الجامع الأموي الكبير في حلب فوق أكبر مدينة في سوريا. في نيسان ٢٠١٣ دمّرت بالكامل.\nهذا المعلم الثمين كان إنعكاس لروح المدينة. وكأنه نصبٌ تذكاري للمدينة ببعدها التاريخي والحضاري وقيمتها الاجتماعية والدينية والثقافيه. مثّلت روح المدينةكمعلماً وشاهداً للزمن على حلب وجموعها وناسها.\nمع مجموعة مميزه من الفنانين، يطرح عرض #ميدنه أو #minaret سؤالاً أساسياً في حياتنا اليومية ويسائل عدم قدرتنا على المبادرة. كيف نتموضع ونقاوم\"الخدر\" والإنكفاء، ونواجه صور الدمار ومصادرة مدن بأكملها والانتهاك اليومي لحميمية الإنسان وخصوصياته؟',1),(2,'Beyond a certain point, movement itself changes',1,1,'2019-04-01 16:20:00',NULL,NULL,1,40,1,'https://www.antoineticketing.com/index.php?event_id=7542','Between two checkpoints, a choreography emerges. A constantly transforming spatial configuration dictates certain movements or restricts it. The question that arises afterwards: Can a planned or organized movement change that space in return? The work explores the spatial dynamics of the Mathaf crossing and its continuous transformation during the Lebanese Civil War up to 1987, when an organized protest dismantled that border for just one day.\n\nبين نقطتَي تفتيش يظهر نوع من الكوريغرافيا. تشكيلات مكانيّة متغيرة تملي حركات معينة أو تقيّدها. هل يمكن لحركةٍ مرسومة أن تغير هذا الفضاء في المقابل؟ يستكشفُ هذا العمل الحركيّات المكانية لمعبر منطقة «المتحف» وتحوّلها المستمر خلال الحرب الأهلية اللبنانية حتى العام 1987، عندما قامت مظاهرة منظمة بتفكيك تلك الحدود ليوم واحد.',2),(3,'Azi Dahaka',1,1,'2019-04-01 16:20:00',NULL,NULL,1,40,1,'https://www.antoineticketing.com/index.php?event_id=7543','Zahak - known to be  an evil figure in Persian mythology, takes on the form of Azi Dahaka in ancient Persian folklore. This tale of Azi Dahaka is one in which a man with human qualities, shape shifts into a terrifying brain-eating dragon, as soon as he is faced with pain, agony, sorrow and fear. It is within this myth that Mitra and Hiva locate themselves, taking the audience into the dark atmosphere of a human’s slow transformation into a monster. Light, fabric, foam and plastic, Azi Dahaka is a visual and somatic rendering of the birth of a monster.',3),(4,'Incontro',1,1,'2019-04-01 16:20:00',NULL,NULL,1,40,1,'https://www.antoineticketing.com/index.php?event_id=7544','Incontro is a dialogue. With their cultural differences at the centre of the practice, Bassam and Jacopo generate an open and exposed debate on cultural diversity for the audience to witness and partake in. This series of encounters between the two artists searches for body-based strategies for identification and qualification. Received with open arms by audiences around Europe, Bassam and Jacopo anticipate how the cultural make-up of the audience can potentially shift their dynamic. Maqamat and BIPOD are happy to have Incontro as a feature performance this year, after having presented it as a works-in-progress in the last edition following a Beirut based residency. Incontro will be presented in Théâtre De La Ville this coming season.\n\nالوصف: Incontro هو حوار، مع اختلافاتهم الثقافية، يطرح بسام وجاكوبو مناقشة مفتوحة ومكشوفة حول التنوع الثقافي على الجمهور ليشاهدها ويشارك فيها. من خلال سلسلة من\nاللقاءات بين هذين الفنانين تبحث عن استراتيجيات جسدية لتحديد الهوية',4),(5,'SET OF SETS',1,1,'2019-04-01 16:20:00',NULL,NULL,1,60,1,'https://www.antoineticketing.com/index.php?event_id=7537','SET OF SETS is an exploration of time as a fundamental concept.  Digging deep into the notion of infinity, GN | MC draws attention to the perception of time, memory and lived experiences as a ceaseless loop with a perpetual & joyous repetition. Generating rhythm and repetition in the movements of bodies, SET OF SETS is a work of art that continually defies gravity through cooperation, precision and rigor, as a metaphor for the repetitive nature of existence itself.\n\nSET OF SETS”\" هو رحلة استكشاف للزمن، باعتباره مفهوما أساسيا. يغوص الفنّانان\nGN | MC عميقاً في معنى اللانهاية، ليسلّطا الضوء على طريقة فهمنا الوقت، واستيعابنا الذاكرة\n والتجارب المعاشة باعتبارها مجتمِعة، حلقة لامتناهية، تواصل مسيرتها وفق تكرار دائم وفرح.\n يتّسم  Set of Sets بالايقاع والتكرار لحركات الاجساد، ليعكس عملا فنياً يتحدّى باستمرار الجاذبية\n من خلال معايير التعاون والدقة والانضباط، بصفتها صورة مجازية تحاكي الطبيعة المتكرّرة للوجود بحدّ ذاته.',5),(6,'KING',1,1,'2019-04-01 16:20:00',NULL,NULL,1,75,1,'https://www.antoineticketing.com/index.php?event_id=7538','Set in a theatrical world, KING is a collaboration between the brilliant minds of award-winning Australian Director & Choreographer Shaun Parker, and internationally renowned Bulgarian-born songwriter & vocalist Ivo Dimchev. Part cocktail lounge and part jungle, KING interrogates intimately, the notion of male power and control. Parker’s choreographic articulation intertwines with the raw and scorching vocals of Dimchev, in a humorous and compelling piece of highly physical dance theatre. \n\nيشكل KING، وهو عمل مسرحي، ثمرة تعاون بين العقلَين النيرَيْن لكلّ من مصمّم الرقص، المخرج الاسترالي الحائز جوائز عدة، شون باركر\nوالمؤلف الموسيقي البلغاري الاصل، صاحب الشهرة العالمية، المغني إيفو ديمتشيف. يتوزّع هذا العمل بين صالة للكوكتيل والادغال حيث يستجوب\nالعرض بشكل عميق وحميمي مفهومَي القوة والسيطرة الذكورية.  يتشابك التعبير الراقص لباركر مع غناء ديمتشيف بصوته العريض والمميّز، ضمن\nلوحة راقصة مرحة وآسرة في الوقت نفسه، تتبلور فيها مهارات جسدية رفيعة المستوى في الرقص المسرحي.',6),(7,'Live',2,1,'2019-04-01 16:20:00',NULL,NULL,1,60,1,'https://www.antoineticketing.com/index.php?event_id=7539','A luscious and heartfelt live act, Ivo Dimchev presents a new solo concert with music from his beloved Sculptures album, and songs yet to be shared with another audience.\n\nCombining his unique stage presence with an etherial voice, Ivo’s Live show incorporates a much-anticipated demeanor that is surely to seduce the audience into a state of trance and wonder.\n\nيقدم إيفو ديمتشيف، وبشكل منفرد، عرضا حيا وحديثا يتّسم بجرعة عالية من الجمال الآسر والفاتن، ويترافق مع موسيقى من ألبومه الناجح\n\" Sculptures \" (منحوتات) وأغنيات لم يؤدِها سابقا. يوفّق عرض إيفو الحيّ بين حضوره الفريد على خشبة المسرح وصوته الأثيري النقيّ،\nليقدّم في نهاية المطاف عرضا من المؤكد أنه سيستحوذ على اعجاب الجمهور، ليتركه في حال من الذهول.',7),(8,'Flow Instrumental',1,1,'2019-04-01 16:20:00',NULL,NULL,1,60,1,'https://www.antoineticketing.com/index.php?event_id=7540','Flow. Inspired by the astonishing performance and ensemble movements of grouped animals, this new creation by Linga explores the formation, velocity and spatial coherence amongst species and their members. From shoals of fish, to swarms of insects, Flow engages in the possibilities of new choreographic dynamics based on collective consciousness, as suggested through the study of these animals and their movement.\nThis performance also marks a first ever collaboration with the French-Korean duet KEDA, who gracefully add a layer of textures rhythms and electronic sounds by means of the ancestral tones of the Geomungo instrument.\n\nFlow (التدفق)، عرض مستلهم من الاداء المذهل لمجموعات من الحيوانات والكائنات الحية وما تقوم به\n من حركات. هذا العمل الجديد الذي يحمل توقيع \"Linga\" يستكشف التكوين والسرعة والتناغم المكاني بين\n الأنواع والفصائل وأفرادها. من أسراب الأسماك إلى الحشرات، ينخرط Flow في احتمالات وديناميكيات\n جديدة لتصميم الرقص، وذلك بالاستناد إلى الوعي والذكاء الجماعيين، وهو المفهوم الذي توصلت اليه الدراسات',8),(9,'Instrumental Creation Geomungo-electro concert',2,1,'2019-04-01 16:20:00',NULL,NULL,1,60,1,'https://www.antoineticketing.com/index.php?event_id=7595','Their music is hypnotic and warm. Their quality has a rhythm of an imaginary folk. Dancing is conjured directly by the sound, beckoning the body to respond. This instrumental creation by E’Joung-Ju and Mathias Delplanque is a meditative and simultaneously energetic experience that will cleanse the audiences auditory palette before filling the room full of an awe-inspiring melodic journey.\n\nإن موسيقاهم دافئة جدا، تبعث على الاسترخاء إلى درجة النوم. وكأن الايقاع الذي يطغى عليها، يعود إلى عالم خيالي. أما الرقص فيأتي أشبه بردّ فعل\nتلقائي تجاه الصوت المنبعِث، فيدعو الجسم الى التفاعل مع الألحان. يشكل الابتكار الذي يقدّمه كلّ من E’Joung-Ju وماتياس، تجربة تأملية وحيوية في\nالوقت نفسه من شأنها أن تنقّي اللوحة السمعية للجمهور، قبل ان تملأ القاعة بألحان، تصطحب عبرها الحضور برحلة مذهلة.',9),(10,'Guerre',1,1,'2019-04-01 16:20:00',NULL,NULL,1,55,1,'https://www.antoineticketing.com/index.php?event_id=7541','War opens up perspectives of matter, while inscribing traces and hues in the flesh of a body. Inspired by the powerful works of writer Yves Klein, GUERRE seeks to transpose the poetic struggle of materials and traces as heard in Klein’s words “The war of the line and of color”, into a visible manifestation of carnal and spatiality. Playing with gravity, space and volume, GUERRE is an insatiable conquest, crossed by the strong tensions between grace and strength, fight or flight.\n\nتفتح الحرب الباب أمام العديد من وجهات النظر المتباينة حول مسائل جوهرية، فيما تواصل نحت آثارها\n في لحم الجسد. إن هذا العرض الرائع مُستلهم من الأعمال الضخمة لإيف كلاين، وعنوانه GUERRE (حرب)،\n وهو يسعى إلى ترجمة الصراع الشعري للمواد والآثار، كما تُسمع في كلمات كلاين، منها مثلا \"حرب\n الخطوط والألوان\". ما من شك في أن التحدّي الابرز يكمن في ترجمة الكلمات إلى مظهر تعبيري،\nيكون مرئيا وجسديا، ويشغل حيّزا مكانيا. يشكل GUERRE ما يشبه الغزو الجارف الذي',10),(11,'Cie LINGA',7,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,'https://www.antoineticketing.com/index.php?event_id=7565','Led by company co-choreographer and co-director Marco Cantalupo, Cie Linga’s workshop will develop a technical work based on the relation to gravity, imbalance, fluidity, energy and the precision of movement. The analysis of the internal paths of the body - of the “anatomy” of gesture - will allow for an exploration of several amplifications and qualities of movement with a new type of authenticity. Throughout the workshop, some choreographic phrases of Cie Linga’s repertoire will be taught and learned in order to further research movement that is fully experienced within the space and context of the workshop.',11),(12,'Partnering & Tools for Creation',7,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,'https://www.antoineticketing.com/index.php?event_id=7564','This workshop is based on the vocabulary and movement generators developed in GN | MC’s creations. We will search for mechanics in movement, observing it in order to multiply paths and approaches. By understanding the complexity of the movement anatomy we will look into the possibilities that the body offers to increase our movement vocabulary. We will be using specific tasks and exercises to prepare the body for this demanding technique, learning many strategies and studying different ways to approach our partner. Within these tools we will be able to create our own patterns within the group and to develop our sense of responsibility and confidence in trusting ourselves and in the others as well as taking risks.',12),(13,'Niemeyer 4 Ever',6,1,'2019-04-01 16:20:00',NULL,NULL,1,30,1,NULL,'Niemeyer 4 Ever draws the poignant picture of the Trablous Rachid Karame International Fair; the “ghost town” composed of a hundred and ten thousand square meters of structures; erected on a one million square meters of terrain; engulfed by bitter souvenirs and dead silence. It never reached the objective for which it was conceived by Oscar Niemeyer, the builder of Brasilia, worldwide celebrated and icon of modern architecture.\n\nيستعيد Niemeyer 4 Ever تاريخا مؤثرا لمعرض طرابلس الدولي؛ المدينة الشبح، المكونة من مليون متر مربع ومئة ألف متر مربع من الإنشاءات؛ والتي يأكلها النسيان، وتغرق في غياهب الذكريات المريرة؛ وهي لم تبلغ أبدا الهدف الذي من أجله أقيمت.\n\nLebanon, 2018- Director: Nicolas Khoury - Producer: BAFF - Beirut Art Film Festival \n\nLanguage: Screenings in Arabic with English on the 5th of April/ French subtitles on the 6th of April',13),(14,'YARA BOUSTANY',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Yara Boustany is an interdisciplinary artist with a unique scope of interests. Focusing on the transformation of words, images and ideas into movement and sound, her background in audio-visuals converses with her dance and theatre training to create a fascinating and imaginative world. In addition to her various researches, she founded a developmental space for performers in Beirut - “Amalgam Studio” - currently in it’s fourth year. Her work ‘‘ēvolvō’ which had it’s successful premiere in BIPOD 2017, has since toured internationally and continues it’s journey into the year.\n\nيارا بستاني هي فنانة متعددة التخصصات تتمتع بنطاق فريد من الاهتمامات. مع التركيز على تحويل الكلمات والصور والأفكار إلى حركة وصوت ، تتحدث خبرتها في مجال الصوتيات المرئية مع تدريباتها الراقصة المسرحية لخلق عالم رائع ومبدع. بالإضافة إلى أبحاثها المختلفة ، قامت بستاني منذ أربع سنوات بتأسيس مساحة تنموية لفناني الأداء في بيروت - \"Amalgam Studio\" - و الجدير بالذكر أن عملها \"ēvolvō\" الذي حظي بنجاح كبير خلال عرضه للمرة الأولى على مسرح BIPOD 2017، يجول عدة مهرجانات عالمية منذ ذلك الحين و حتى عامنا الحالي.',14),(15,'HAMDI DRIDI',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Whether on rooftops, tram platforms, weddings or theatres, one thing is certain about Hamdi Dridi’s work - it is awe inspiring. With roots in the hip-hop vernacular, this Tunisian artist has created a repertory of works that keep the audience asking for more. Working with the likes of Sybel Ballet Theatre, Maguy Marin and CNDC Angers, Hamdi’s interest and sensitivity to musicality of the spoken word, sounds and texts has led him to the International Choreographic Institue ICI-CCN of Montpellier, where he continues to refine his choreographic vocabulary.\n\nسواء اعتلت أعماله أسطح المباني أو محطات القطارات أو المسارح أو زيّنت حفلات الأعراس، هناك شيء واحد مؤكد مع حمدي دريدي، ألا وهو تقديمه عرضا مذهلا ومُلهما. ابتكر هذا الفنان التونسي المتجذّر في فن الهيب هوب العامي، مجموعة من اللوحات الراقصة التي تجعل الجمهور متعطّشا لطلب المزيد منها. لقد تعاون حمدي مع العديد من الجهات المرموقة، منها مسرح سيبال للباليه Sybel Ballet Theatre، مصمّمة الرقص الفرنسية غيي ماران، المركز الوطني للرقص المعاصر في فرنسا (CNDC Angers). أما اهتمامه وولعه بنغمات الكلمات والاصوات والنصوص، فقد قاداه الى \"المعهد الدولي لتصميم الرقص\" ICI-CCN في مونبولييه حيث يواصل صقل موهبته وأسلوبه في تصميم الرقص.',15),(16,'STEPHANIE KAYAL',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Stephanie Kayal is recognized for her ability to partake and fully commit to the two worlds of dance and theater. With a diverse sets of skills and techniques, she has worked as a performer and teacher locally and internationally. From PARTS residencies to the works of Jorges Crecis, Min Tala Dance Company, Ryan Jojokarso, Koon Theater Group at the Theatre National de Wallonie Bruxelles , Kayal has embarked on an incredible journey and career taking the plunge into the world of creation in 2017 with her solo premiere at Dancing on The Edge festival. Maqamat is excited to present Kayal’s work in progress. \n\nستيفاني كيّال ممثّلة وراقصة مقيمة في بيروت. قادت ورشات عمل في الرقص المعاصر، وشاركت في أعمالٍ مسرحيّة في لبنان، فرنسا، بلجيكا، ألمانيا، هولاندا، تشيلي، تونس، الجزائر، مصر، وعمان. عملت مع مخرجين، مصممي رقص، وفرق مسرحية منها: خورخيه كريسيس، فرقة من تلا للرقص المعاصر، رايان يويوكارسو، ناوهيكو اوميواكا وآخرها كان إنتاجاً للمسرح الوطني والونيا من إخراج فرقة كون المسرحية. شاركت في إقامات فنّيّة في فضاءات مختلفة منها: دوكس (أوتريخت)، بارتس (بروكسيل)، وتكوين / مقامات (بيروت). كما قدّمت أوّل مشروع سولو من تصميمها وأدائها في أمستردام ولاهاي في مهرجان \"دانسينغ أون ذي إدج\" هولاندا ٢٠١٧.',16),(17,'JADD TANK',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Jadd Tank’s passion for choreography and dance lies in the exploration of boundless possibilities in which objects, bodies and events relate to each other. Jadd has performed internationally with his own work and those of Maqamat Dance Company, Alias Guilherme Botelho and Michelle Ellsworth. He has been a featured choreographer and performer for the 2017 / 2018 Focus on Mediterranean series in Italy, and is currently working on a new series of performances for live and virtual spaces. \n\nيكمن شغف \"جاد تانك\" بالرقص والكوريغرافيا في استكشاف إمكانات لا حدود لها تترابط فيها الأشياء والأجساد والأحداث بعضها ببعض. والفرقة قدّمت عروضها على مستوى العالم بأعمالها الخاصة وأعمال \"مقامات\" وآلياس غييرميه بوتيليو ومارسيل ليمان وميشيل إيلزوورث. كذلك، شاركت الفرقة تصميماً ورقصاً عامي 2017 و 2018 في سلسلة \"تركيز على المتوسط\"  Focus on Mediterranean في إيطاليا، وتعمل حالياً على عرض جديد للمساحات الحية والافتراضية.',17),(18,'NIVINE KALLAS',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Nivine Kallas is a multidisciplinary artist. Born in Beirut, she started her training with Caracalla Dance Theatre, followed by a BA degree in Drama from the Lebanese University of Fine Arts. With a background in Lebanese Folklore, oriental, modern, and contemporary dance, Nivine has performed in dance theatre musicals and various projects locally and internationally. A member of Sima Dance company between 2014 and 2015, she has participated in workshops conducted by local and international choreographers in Spain, Paris and Lebanon. \n\n بدأت تلقي دروس الرقص من عمر الرابعة عشرة من مدرسة كاركلا للمسرح الراقص. ثم اكملت مسيرتها في الجا اللبنانية-معهد الفنون الجميلة(الفرع الثاني)محصلة اجازة في التمثيل .تعرف نيفين بامتلاكها خلفية غنية من     مدارس الرقص،(الفلكلو اللبناني،الشرقي،الرقص المعاصر) نيفين الكلاس، ممثلة و راقصة محترفة من بيروت شاركت في عدد من الاعمال المسرحية الراقصة في لبنان والعالم العربي وكانت جزءا من مشاريع فنية تحت إدارة محترفين وشركات كثر . عضو في مؤسسة سيما للرقص المعاصر بين سنة\"٢٠١٤-٢٠١٥\". شاركت نيفين في محترفات للتعب الجسدي الراقص مع مصممين رقص محلين وعالمين بين لبنان وإسبانيا  وباريس .',18),(19,'HIVA DEDAGHAT',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'for 5 years. In 2012, she got familiar with the contemporary dance form and participated in various international and local workshops. She has been part of various creations and continues to create with the ‘MAHA’ group based in Iran. Recently, Hiva created a duet with Mitra Ziee Kia titled “Through the skin” -  a production of Maqamat - which has been presented in multiple festivals. Currently, Hiva has a new creation in the works which she intends to premiere later this year. \n\nتمارس هيفا، التي درست الهندسة المعمارية، العزف على الكمنجة الإيرانية منذ خمس سنوات. في العام 2012، تعرفت على الرقص المعاصر وشاركت في ورش عمل دولية ومحلية مختلفة. وقد شاركت في وضع عدد من الأعمال، وتواصل إبداعها الفني مغ فرقة ‘MAHA’. وقد ألّفت عملاً ثنائياً مع ميترا زي كيا بعنوان  Through the skin  (عبر الجلد)، من إنتاج \"مقامات\"، وتم تقديمه في مهرجانات عدة. وحالياً لهيفا عمل جديد قيد الإعداد.\n\n',19),(20,'MITRA ZIAEE KIA',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'members in ‘MAHA’ group - a collective outcome of her and other members in the field. Recently she has worked with ‘Maqamat’ and performed in the dance piece ZAAFARAN, a co-collaboration between  ‘Maqamat’ and ‘MaHa’. In addition, Mitra created a duet with Hiva Sedaghat titled “Through the skin” -  a production of Maqamat - currently on tour. Mitra has a new creation in the works. \n\nعملت ميترا، التي درست التمثيل، على مسارح في إيران وبلدان عدة. وهي عضو في فرقة ‘MAHA’، التي تجمعها وراقصين آخرين. وقد عملت حديثاً مع \"مقامات\"، ورقصت في العمل المعنون \"زعفران\"، وهو ثمرة تعاون بين مقامات و‘MAHA’. يضاف إلى ذلك أنها ألفت مع هيفا صداقات \"عبر الجلد\"، من إنتاج \"مقامات\"، وتقومان بجولة به حالياً. ولميترا عمل جديد قيد الإعداد.',20),(21,'CHARLIE PRINCE',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Charlie Prince is a living example of hard-earned talent. Beirut Born - Amsterdam Based, Charlie’s capacity to engage audiences through his physicality and aesthetics has manifested on many stages both local and international. His interests are in the placement of the body within the post-colonial imagination as a means of subverting and resisting imperial legacies. He has worked with the likes of Ballet BC, Cie Alias, Cie Irina Baldini and Maqamat Dance Theatre since 2015. Charlie is the 2018 Boghossian Foundation Prize for Dance and Performance recipient, and is currently supported by APAP, with works showcasing in Dansmakers, OktoberDans and the BIPOD Festival. \n\nيشكّل تشارلي برنس مثالا حيا للموهبة التي صُقلت بفضل بذل جهود كبيرة. تشارلي مولود في بيروت ومقيم في أمستردام، يتمتع بقدرة على جعل الجمهور يتفاعل مع فنه بفضل مهاراته الجسدية والجمالية. وقد برز ذلك بوضوح على العديد من المسارح المحلية والعالمية.   ينشغل في وضع الجسد ضمن عالم خيالي، يتموضع في مساحة ما بعد مرحلة الاستعمار، وذلك ليقدّم وسيلة لتقويض دعائم النظام الإمبريالي ومقاومته.  لقد تعاون مع العديد من الجهات المرموقة في عالم تصميم الرقص منها Ballet BC، Cie Alias، Cie Irina Baldini، وعمر راجح | مقامات منذ العام 2015. كما حاز تشارلي جائزة \"مؤسسة بوغوصيان للرقص والاداء\"، ويحظى حاليا بدعم من مقامات عبر الشبكه الإوروبيه APAP، فيما تُعرض أعماله على منصة  Dansmakers (هولندا)، مهرجان \"OktoberDans\" للرقص (النروج)، إضافة إلى مهرجان \"بايبود\" (لبنان).',21),(22,'BASSAM ABOU DIAB',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Bassam Abou Diab knows no limitations. An up & coming choreographer from Beirut Lebanon, Bassam has utilized the Moultaqa Leymoun platform to its full potential. His history and interest in theatre, folklore and contemporary dance has propelled him into the regional and international dance scene as a force to be reckoned with. Having worked under the guidance of many established artists, Bassam’s cultural influences and adaptability creates in his work, a captivating sense of wonder, leaving the audience wanting more. Bassam is an Associated Artist with Maqamat and is currently supported by Maqamat Beit El Raqs alongside APAP for the APAP 2020 project.\n\n بسام أبو دياب لا يعرف أي قيود في أعماله، هو مصمم رقص و مخرج من بيروت اللبنانية ، استعان بسام ب \"ملتقى ليمون\" بكامل إمكانياته ليكون منطلقاً له - وقد قدم الكثير من العروض الدولية في السنوات الأخيرة. دفعه تاريخه واهتمامه بالمسرح والفولكلور والرقص المعاصر إلى مسرح الرقص الإقليمي والدولي كقوة لا يستهان بها. وقد عمل تحت إشراف عمر راجح مقامات ، جيل جوبين ، داميان جاليه ، ماركو كانتالوبو و فنانين آخرين، تأثيرات بسام الثقافية والقدرة على التكيف تخلق في عمله إحساسًا ساحرًا و مميز، تاركة الجمهور بإنتظار المزيد. بسام هو   .APAP  ضمن مشروع  2020 APAP فنان مرتبط بمقامات ويدعمه حالياً مقامات بيت الراقص إلى جانب \n\n',22),(23,'GACIA TOKAJIAN',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Gacia’s passion for dancing started when she joined the National Centre for Culture and Dance (NCCA) in 1997 and started training as a classical/contemporary dancer. One of the first members of MISK Dance company in 2008, Gacia performed in many festivals locally and internationally including Jordan, Bahrain, Turkey, while participating in numerous contemporary dance workshops with international choreographers. She has also been teaching Ballet at the NCCA for various ages and levels for the past 8 years. In 2009, she took part in the renowned musical \"Petra Rocks\" as a main dancer.\n\nبدأ شغف غاسيا بالرقص عندما انضمت إلى المركز الوطني للثقافة والفنون في عام 1997 وبدأت التدرب كراقصة كلاسيكية/معاصرة. انضمت إلىى فرقة مسك للفنون الأدائية في عام 2008 وقدمت عروضاً في العديد من المهرجانات المحلية والدولية بما في ذلك الأردن والبحرين وتركيا. شاركت غاسيا في العديد من ورشات الرقص المعاصرة مع مصممي رقص عالميين. كما أنها تدرِس الباليه منذ 8 سنوات. في عام 2009 ، شاركت غاسيا في المسرحية الموسيقية الشهيرة \"بترا ان حكت\" كراقصة رئيسية.',23),(24,'CYNTHIA TOJAKIAN',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Involved in performing arts since a very young age, Cynthia Tokajian has been training at the National Centre for Culture and Arts (NCCA) for many years, mainly in classical Ballet, contemporary and folk dance. She has been a member of MISK Dance Company since 2011, performing in many festivals locally and internationally including Jordan, Bahrain, Turkey and Italy. Cynthia choreographed one of her latest contemporary dance works \"Nostalgia\" which premiered at the annual Amman Contemporary Dance Festival in 2018. For the past 4 years, Cynthia has been teaching Ballet and modern dance to students throughout Jordan. \n\nبدأت سنثيا رحلتها في فنون الرقص منذ سن مبكر في المركز الوطني للثقافة والفنون في الباليه الكلاسيكي والرقص المعاصر والرقص الشعبي. انضمت الى فرقة مسك للفنون الأدائية في عام 2011، وشاركت في العديد من المهرجانات المحليًة والعالميًة في الأردن والبحرين وتركيا وإيطاليا. عرض من أحدث أعمالها المعاصرة \"الحنين الى الماضي\" في مهرجان عمان للرقص المعاصر في عام 2018. منذ السنوات الأربعة الماضية ، تقوم سينثيا بتدريس الباليه والرقص الحديث.',24),(25,'RAMZ SAYYAM',8,1,'2019-04-01 16:20:00',NULL,NULL,1,120,1,NULL,'Ramz Siam is a Palestinian dancer born in Jerusalem. With a degree in Political Science and Women’s Studies, she spent 10 years studying dance and in 2013, joined the Douban Dance group, was a part of various local dance productions, including Sideways Rain by ALIAS, giving her the opportunity to tour in Beirut, Jordan, Ramallah and Jerusalem. With more experience under her wing, Ramz studied at the National Academy of Dance in Rome, where she worked with international artists including Koffi Koko. Currently Ramz is working with stereo 48 - a Palestinian dance company - between Palestine and France. ',25),(26,'Rush of Adrenaline',9,1,'2019-04-01 16:20:00',NULL,NULL,1,90,1,NULL,'Artists are constantly responding to their environments. It goes without saying that the provision of space directly affects the artist’s approach to exploration, creation and presentation of their work. Where do contemporary art institutions fall in this? Is there a method of codifying the sustainment and support of dance artists within our current structures of spaces without sacrificing the artistic integrity? Or, are we caught in a static version of our former selves? With these questions in mind, we look to truly rethink and break traditional perceptions of artistic spaces, finding new ways of self-sustainability to support creative integrity and redefine such spaces as ways of life versus preservations of what once was. \n\nThe discussion panels will feature  international guests ( TBA) and are open to the public. ',26);
/*!40000 ALTER TABLE `tbl_event_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_event_class_cast_and_credit`
--

DROP TABLE IF EXISTS `tbl_event_class_cast_and_credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class_cast_and_credit` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(41) NOT NULL,
  `DESCRIPTION` longtext NOT NULL,
  `EVENT_CLASS` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_43564352347567235_idx` (`EVENT_CLASS`),
  CONSTRAINT `FK_43564352347567235` FOREIGN KEY (`EVENT_CLASS`) REFERENCES `tbl_event_class` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class_cast_and_credit`
--

LOCK TABLES `tbl_event_class_cast_and_credit` WRITE;
/*!40000 ALTER TABLE `tbl_event_class_cast_and_credit` DISABLE KEYS */;
INSERT INTO `tbl_event_class_cast_and_credit` VALUES (1,' ','~~TEST~~Concept & Choreography Omar Rajeh\r Music Composition Mahmoud Turkmani & Pablo Palacio\r Performed by Antonia Kruschel, Charlie Prince, Mia Habis, Moonsuk Choi,\r Omar Rajeh, Yamila Khodr\r Live Music by Joss Turnbull (percussion), Mahmoud Turkmani (oud), Pablo\r Palacio (interactive sonifcation), Ziad El Ahmadie (oud)\r Real Time Movement Analysis Technology Instituto Stocos\r Voice Naim Asmar\r Lighting Guy Hoare\r Video artist Ygor Gama / Dafne Narvaez Berlfein\r Drone operator Hadi Bou Ayash\r Costumes Mia Habis\r Technical director Christian Francois\r Sound engineer Philippe Balzé / Jean -Christophe Batut\r Stage manager Salim Abou Ayash / Samah Tarabay\r Tour Manager Jadd Tank\r A production of Omar Rajeh | Maqamat\r Co-produced by Romaeuropa Festival, BIPOD, HELLERAU – European\r Center for the Arts Dresden with the support of Tanzfabrik Berlin, Charleroi\r danse-centre chorégraphique de Wallonie-Bruxelles, Tanzmesse nrw and\r apap – advancing performing arts project\r Dance floor kindly supplied by Harlequin Flooring\r www.omarrajeh.com | www.maqamat.org',1),(2,' ','A performance by Ghida Hachicho\nin collaboration with Shakeeb Abu Hamdan\n\nFunded by AFAC | Arab Fund for Arts and Culture\n\nCo-produced by Citerne Beirut & BIPOD Festival\n\nwith the support of Maqamat, APAP and Ashkal Alwan',2),(3,' ','Choreographed and performed by: Mitra Ziaee Kia & Hiva Sedaghat\n\nTechnical Director and lighting designer: Ali Kouzehgar\n\nCo-produced by Citerne Beirut & Bipod Festival with the support of Maqamat',3),(4,' ','Choreographed and performed by Bassam Abou Diab & Jacopo Jenna.\nProduction: KLM – Kinkaleri, LeSupplici, MK\nPhoto Credit: Alexander Corciulo\nCo-produced by Citerne Beirut and BIPOD festival, with the support of\nFabbrica Europa & Maqamat.\nPerformance in Beirut supported by Istituto Italiano di Cultura- Beirut\nwww.jacopoj.it | www.maqamat.org',4),(6,' ','Idea/concept GN|MC - Guy Nader | Maria Campos\n\nDirection Guy Nader\n\nCreation/performance Maria Campos, Guy Nader, Lisard Tranis, Clémentine Telesfort, Hector Plaza, Alfonso Lopez Aguilar, Tina Halford\n\nMusic Miguel Marín\n\nLight design Abulafia\n\nTechnical direction Albert Glas, Conchita Pons\n\nCostume Anna Ribera\n\nArtistic advice Alexis Eupierre\n\nRehearsal assistance Tanja Skok\n\nProduction Raqscene, Elclimamola\n\nCo-production Mercat de les Flors, Festival Sismògraf, Julidans Festival\nSupport Graner, La Caldera, Les Brigittines – Centre d’Art contemporain du Mouvement de la Ville de Bruxelles\nCollaboration Departament de Cultura - Generalitat de Catalunya, Ministerio de Cultura.\n\nwww.gn-mc.com',5),(7,' ','Director & Choreographer: Shaun Parker\n\nComposer, Songwriter & live Vocalist: Ivo Dimchev Dancers: Josh Mu, Toby Derrick, Libby Montilla, Imanuel Dado, Joel Fenton, Samuel Beazley, Harrison Hall, Robert Tinning, Alex Warren and Damian Meredith\n\nLighting Design: Benjamin Brockman\n\nBrass/Cello Arrangements: Duane Morrison/Nils Hob\n\nEarly Development Dramaturg: Veronica Neave\n\nAssociate Producer: James Beach\n\nRehearsal Director: Josh Mu\n\nProduction Manager: Mark Haslam\n\nSound Engineer:  Ilia Bezroukov\n\nStage Manager:  Imogen Bouchier\n\nLogo Acknowledgements: Australia Council for the Arts | Create NSW | Seymour Centre | Shaun Parker & Company | Council of Arab and Australian Relations\n\nShaun Parker & Company’s tour of KING is supported by the Commonwealth through the Council for Australian-Arab Relations, which is part of the Department of Foreign Affairs and Trade.\n\nwww.shaunparkercompany.com',6),(8,' ','Music and text: Ivo Dimchev   \n\nMusic Production: Georgy Linev, Sash & Blazh, Ivo Dimchev\n\nProduction: Humarts Foundation (Sofia - BG)\n\nManagement and international distribution: Something Great (Berlin - DE)\n\nwww.ivodimchev.com |  www.somethinggreat.de',7),(9,' ','Concept and Choreography: Katarzyna Gdaniec and Marco Cantalupo\nDancers: Aude-Marie Bouchard, Marti Güell Vallbona, Ai Koyama, Andor Rusu,\nManuela Spera, Csaba Varga, Cindy Villemin\nCoproduction: Compagnie Linga, L’Octogone Théâtre de Pully\nLight Conception: German Schwab\nMusic: KEDA (Mathias Delplanque and E’Joung-Ju)\nSet Design: Emilien Allenbach, Geneviève Mathier, Grégory Gaulis\nCostume Design: Geneviève Mathier\nAdministration: Françoise Oehrli\nPR & Communication: Fabien Jakob\nCompagnie Linga benefits from a joint cooperative support agreement from\nthe City of Pully, the City of Lausanne, the Canton of Vaud while Pro Helvetia\n- Swiss Cultural Foundation’s and Corodis’ contributions go towards\ninternational tour.\nwww.linga.ch/en/company',8),(10,' ','Live Music by\nMathias Delplanque, electronic music\nE\'Joung Ju, geomungo',9),(11,' ','Conception & Choreography : Samuel Mathieu',10),(13,'Cie Linga:','Marco Cantalupo',11),(14,'Partnering & Tools for creation:','Guy Nader | Maria Campos',12),(16,'BY:','Niemeyer 4 Ever',13),(17,'BY:','YARA BOUSTANY',14),(18,'BY:','HAMDI DRIDI',15),(19,'BY:','STEPHANIE KAYAL',16),(20,'BY:','JADD TANK',17),(21,'BY:','NIVINE KALLAS',18),(22,'BY:','HIVA DEDAGHAT',19),(23,'BY:','MITRA ZIAEE KIA',20),(24,'BY:','CHARLIE PRINCE',21),(25,'BY:','BASSAM ABOU DIAB',22),(26,'BY:','GACIA TOKAJIAN',23),(27,'BY:','CYNTHIA TOJAKIAN',24),(28,'BY:','RAMZ SAYYAM',25),(29,'Rush of Adrenaline:','Reinjecting life into Performing Art Spaces',26),(30,'BY:','sfddf',1);
/*!40000 ALTER TABLE `tbl_event_class_cast_and_credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_event_class_category`
--

DROP TABLE IF EXISTS `tbl_event_class_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class_category` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(21) NOT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class_category`
--

LOCK TABLES `tbl_event_class_category` WRITE;
/*!40000 ALTER TABLE `tbl_event_class_category` DISABLE KEYS */;
INSERT INTO `tbl_event_class_category` VALUES (1,'Dance','2019-03-24 19:45:52',NULL,NULL),(2,'Music','2019-03-24 19:45:52',NULL,NULL),(3,'Theater','2019-03-24 19:45:52',NULL,NULL),(4,'Lecture','2019-03-24 19:45:52',NULL,NULL),(5,'Class','2019-03-24 19:45:52',NULL,NULL),(6,'Film','2019-03-24 19:45:52',NULL,NULL),(7,'Workshop','2019-03-24 19:45:52',NULL,NULL),(8,'Studio Presentations','2019-03-24 19:45:52',NULL,NULL),(9,'Discussion Panel','2019-03-24 19:45:52',NULL,NULL);
/*!40000 ALTER TABLE `tbl_event_class_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_event_class_country`
--

DROP TABLE IF EXISTS `tbl_event_class_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class_country` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(21) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class_country`
--

LOCK TABLES `tbl_event_class_country` WRITE;
/*!40000 ALTER TABLE `tbl_event_class_country` DISABLE KEYS */;
INSERT INTO `tbl_event_class_country` VALUES (1,'Lebanon'),(2,'Iran'),(3,'Lebanon / Italy'),(4,'Lebanon/Spain'),(5,'Australia'),(6,'Bulgaria');
/*!40000 ALTER TABLE `tbl_event_class_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_event_class_image`
--

DROP TABLE IF EXISTS `tbl_event_class_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class_image` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(230) DEFAULT NULL,
  `PATH` varchar(200) DEFAULT NULL,
  `IMAGE_INDEX` int(4) DEFAULT NULL,
  `EVENT_CLASS` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_875468756287364872_idx` (`EVENT_CLASS`),
  CONSTRAINT `FK_875468756287364872` FOREIGN KEY (`EVENT_CLASS`) REFERENCES `tbl_event_class` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class_image`
--

LOCK TABLES `tbl_event_class_image` WRITE;
/*!40000 ALTER TABLE `tbl_event_class_image` DISABLE KEYS */;
INSERT INTO `tbl_event_class_image` VALUES (21,'Image 1','omar.png',1,1),(22,'Image 2','citerne1.png',2,1),(23,'Image 3','citerne2.png',3,1),(24,'Image 4','citerne3.png',4,1),(25,'Image 1','ghida.png',1,2),(27,'Image 1','event3.png',1,3),(28,'Image 1','event4.png',1,4),(29,'Image 1','event5.png',1,5),(30,'Image 1','event6.png',1,6),(31,'Image 1','event7.png',1,7),(32,'Image 1','event8.png',1,8),(33,'Image 1','event9.png',1,9),(34,'Image 1','event10.png',1,10),(36,'Image 1','guy.png',2,5),(37,'Image 1','parker.png',2,6),(38,'Image 1','parker2.png',3,6),(39,'Image 1','samuel.png',2,10),(40,'Image 1','samuel2.png',3,10),(41,'Image 1','yara.png',1,14),(42,'Image 1','hamdi.png',1,15),(43,'Image 1','stephanie.png',1,16),(44,'Image 1','jadd.png',1,17),(45,'Image 1','jadd2.png',2,17),(46,'Image 1','jadd3.png',3,17),(47,'Image 1','nivine.png',1,18),(48,'Image 1','hiva.png',1,19),(49,'Image 1','mitra.png',1,20),(50,'Image 1','charlie.png',1,21),(51,'Image 1','charlie2.png',2,21),(52,'Image 1','bassam.png',1,22),(53,'Image 1','gacia.png',1,23),(54,'Image 1','cynthia.png',1,24),(55,'Image 1','ramz.png',1,25),(56,'Image 1','neimar.png',1,13),(57,'Image 1','marco.png',1,11),(58,'Image 1','guy2.png',1,12),(59,'Image 1','general.png',1,26);
/*!40000 ALTER TABLE `tbl_event_class_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_event_class_media`
--

DROP TABLE IF EXISTS `tbl_event_class_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class_media` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EVENT_CLASS` bigint(20) NOT NULL,
  `NAME` varchar(21) NOT NULL,
  `PATH` varchar(1001) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_143564352347567235_idx` (`EVENT_CLASS`),
  CONSTRAINT `FK_143564352347567235` FOREIGN KEY (`EVENT_CLASS`) REFERENCES `tbl_event_class` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class_media`
--

LOCK TABLES `tbl_event_class_media` WRITE;
/*!40000 ALTER TABLE `tbl_event_class_media` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_event_class_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_event_class_profiles`
--

DROP TABLE IF EXISTS `tbl_event_class_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class_profiles` (
  `EVENT_CLASS_ID` bigint(20) NOT NULL,
  `PROFILE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`EVENT_CLASS_ID`,`PROFILE_ID`),
  KEY `FK_8548376823654623785_idx` (`PROFILE_ID`),
  CONSTRAINT `FK_532685723423432` FOREIGN KEY (`EVENT_CLASS_ID`) REFERENCES `tbl_event_class` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_8548376823654623785` FOREIGN KEY (`PROFILE_ID`) REFERENCES `tbl_profile` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class_profiles`
--

LOCK TABLES `tbl_event_class_profiles` WRITE;
/*!40000 ALTER TABLE `tbl_event_class_profiles` DISABLE KEYS */;
INSERT INTO `tbl_event_class_profiles` VALUES (1,1),(2,2),(3,3),(4,4),(5,14),(12,14),(6,15),(7,16),(8,17),(9,18),(10,19),(14,20),(11,21),(13,22),(15,23),(16,24),(17,25),(18,26),(19,27),(20,28),(21,29),(22,30),(23,31),(24,32),(25,33),(26,34),(3,35),(4,36),(5,37),(12,37),(8,38);
/*!40000 ALTER TABLE `tbl_event_class_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_event_class_schedule`
--

DROP TABLE IF EXISTS `tbl_event_class_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class_schedule` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CLASS_DAY_INDEX` tinyint(1) DEFAULT NULL,
  `TIME` time DEFAULT NULL,
  `EVENT_CLASS` bigint(20) NOT NULL,
  `SHOW_DATETIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_432542343534241_idx` (`EVENT_CLASS`),
  CONSTRAINT `FK_432542343534241` FOREIGN KEY (`EVENT_CLASS`) REFERENCES `tbl_event_class` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class_schedule`
--

LOCK TABLES `tbl_event_class_schedule` WRITE;
/*!40000 ALTER TABLE `tbl_event_class_schedule` DISABLE KEYS */;
INSERT INTO `tbl_event_class_schedule` VALUES (14,NULL,NULL,1,'2019-04-04 19:30:00'),(15,NULL,NULL,1,'2019-04-05 21:00:00'),(16,NULL,NULL,2,'2019-04-05 19:00:00'),(17,NULL,NULL,3,'2019-04-06 19:00:00'),(18,NULL,NULL,4,'2019-04-06 20:00:00'),(19,NULL,NULL,5,'2019-04-06 21:00:00'),(20,NULL,NULL,6,'2019-04-09 20:30:00'),(21,NULL,NULL,7,'2019-04-10 20:30:00'),(22,NULL,NULL,8,'2019-04-11 20:30:00'),(23,NULL,NULL,9,'2019-04-12 20:30:00'),(24,NULL,NULL,10,'2019-04-13 20:30:00'),(26,NULL,NULL,11,'2019-04-10 17:30:00'),(27,NULL,NULL,12,'2019-04-07 11:00:00'),(28,NULL,NULL,13,'2019-04-05 18:00:00'),(29,NULL,NULL,13,'2019-04-06 18:00:00'),(30,NULL,NULL,14,'2019-04-05 10:00:00'),(31,NULL,NULL,15,'2019-04-05 10:00:00'),(32,NULL,NULL,16,'2019-04-05 10:00:00'),(33,NULL,NULL,17,'2019-04-05 10:00:00'),(34,NULL,NULL,18,'2019-04-05 10:00:00'),(35,NULL,NULL,19,'2019-04-05 10:00:00'),(36,NULL,NULL,20,'2019-04-05 10:00:00'),(37,NULL,NULL,21,'2019-04-06 10:00:00'),(38,NULL,NULL,22,'2019-04-06 10:00:00'),(39,NULL,NULL,23,'2019-04-06 10:00:00'),(40,NULL,NULL,24,'2019-04-06 10:00:00'),(41,NULL,NULL,25,'2019-04-06 10:00:00'),(42,NULL,NULL,26,'2019-04-05 12:00:00'),(43,NULL,NULL,26,'2019-04-06 12:00:00');
/*!40000 ALTER TABLE `tbl_event_class_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_event_class_type`
--

DROP TABLE IF EXISTS `tbl_event_class_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event_class_type` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(21) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event_class_type`
--

LOCK TABLES `tbl_event_class_type` WRITE;
/*!40000 ALTER TABLE `tbl_event_class_type` DISABLE KEYS */;
INSERT INTO `tbl_event_class_type` VALUES (1,'Event'),(2,'Class');
/*!40000 ALTER TABLE `tbl_event_class_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_favorite`
--

DROP TABLE IF EXISTS `tbl_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_favorite` (
  `ID` bigint(20) NOT NULL,
  `USER` bigint(20) NOT NULL,
  `EVENT_CLASS` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_87258723687562783587_idx` (`USER`),
  KEY `FK_8978745726374627635_idx` (`EVENT_CLASS`),
  CONSTRAINT `FK_87258723687562783587` FOREIGN KEY (`USER`) REFERENCES `tbl_user_profiles` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_8978745726374627635` FOREIGN KEY (`EVENT_CLASS`) REFERENCES `tbl_event_class` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_favorite`
--

LOCK TABLES `tbl_favorite` WRITE;
/*!40000 ALTER TABLE `tbl_favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_general_dashboard`
--

DROP TABLE IF EXISTS `tbl_general_dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_general_dashboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_key` varchar(45) NOT NULL,
  `counter_value` longtext,
  `item_type` tinyint(1) NOT NULL,
  `query` longtext NOT NULL,
  `filters` varchar(45) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `colors` varchar(200) DEFAULT NULL,
  `xaxiscolumn` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_general_dashboard`
--

LOCK TABLES `tbl_general_dashboard` WRITE;
/*!40000 ALTER TABLE `tbl_general_dashboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_general_dashboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_groups`
--

DROP TABLE IF EXISTS `tbl_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_groups` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(256) NOT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_groups`
--

LOCK TABLES `tbl_groups` WRITE;
/*!40000 ALTER TABLE `tbl_groups` DISABLE KEYS */;
INSERT INTO `tbl_groups` VALUES (1,'Installer Group','2018-01-22 10:26:16',NULL,'installer group',NULL),(2,'Support Group','2018-01-22 10:25:16',NULL,NULL,NULL),(3,'All Administrator Groups','2018-01-22 10:30:16','2018-09-25 15:43:07','admin group',NULL),(9,'Citerne Admin','2018-01-22 10:30:16',NULL,'citerne admin group',NULL),(10,'Citerne User','2018-01-22 10:30:16',NULL,'citerne user group',NULL);
/*!40000 ALTER TABLE `tbl_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_groups_reports`
--

DROP TABLE IF EXISTS `tbl_groups_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_groups_reports` (
  `GROUP_ID` bigint(20) NOT NULL,
  `REPORT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`GROUP_ID`,`REPORT_ID`),
  KEY `GROUPS_ROLES_FK2_idx` (`REPORT_ID`),
  CONSTRAINT `GROUPS_REPORTS_FK1` FOREIGN KEY (`GROUP_ID`) REFERENCES `tbl_groups` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `GROUPS_REPORTS_FK2` FOREIGN KEY (`REPORT_ID`) REFERENCES `tbl_reports` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_groups_reports`
--

LOCK TABLES `tbl_groups_reports` WRITE;
/*!40000 ALTER TABLE `tbl_groups_reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_groups_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_groups_roles`
--

DROP TABLE IF EXISTS `tbl_groups_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_groups_roles` (
  `GROUP_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`GROUP_ID`,`ROLE_ID`),
  KEY `GROUPS_ROLES_FK2` (`ROLE_ID`),
  CONSTRAINT `GROUPS_ROLES_FK1` FOREIGN KEY (`GROUP_ID`) REFERENCES `tbl_groups` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `GROUPS_ROLES_FK2` FOREIGN KEY (`ROLE_ID`) REFERENCES `tbl_roles` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_groups_roles`
--

LOCK TABLES `tbl_groups_roles` WRITE;
/*!40000 ALTER TABLE `tbl_groups_roles` DISABLE KEYS */;
INSERT INTO `tbl_groups_roles` VALUES (1,1),(1,2),(2,2),(1,3),(3,3),(1,4),(3,4),(1,5),(3,5),(1,6),(3,6),(1,7),(3,7),(1,8),(3,8),(1,9),(3,9),(1,10),(3,10),(1,11),(3,11),(1,12),(3,12),(1,13),(3,13),(1,14),(3,14),(1,15),(3,15),(1,16),(3,16),(1,17),(3,17),(1,18),(3,18),(1,19),(3,19),(9,20),(10,21),(3,22);
/*!40000 ALTER TABLE `tbl_groups_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_languages`
--

DROP TABLE IF EXISTS `tbl_languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_languages` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `prefix` varchar(5) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_languages`
--

LOCK TABLES `tbl_languages` WRITE;
/*!40000 ALTER TABLE `tbl_languages` DISABLE KEYS */;
INSERT INTO `tbl_languages` VALUES (1,'English','en'),(2,'Francais','fr'),(3,'عربي','ar');
/*!40000 ALTER TABLE `tbl_languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_notification_events`
--

DROP TABLE IF EXISTS `tbl_notification_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_notification_events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) NOT NULL DEFAULT '0',
  `web_notification_flag` tinyint(4) NOT NULL DEFAULT '0',
  `sms_notification_flag` tinyint(4) NOT NULL DEFAULT '0',
  `email_notification_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_notification_events`
--

LOCK TABLES `tbl_notification_events` WRITE;
/*!40000 ALTER TABLE `tbl_notification_events` DISABLE KEYS */;
INSERT INTO `tbl_notification_events` VALUES (1,'ADD_USER',1,0,0),(2,'UPDATE_USER',1,0,0),(3,'DELETE_USER',1,0,0);
/*!40000 ALTER TABLE `tbl_notification_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_notification_texts`
--

DROP TABLE IF EXISTS `tbl_notification_texts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_notification_texts` (
  `text_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL DEFAULT '0',
  `language_id` int(11) NOT NULL DEFAULT '0',
  `text` varchar(200) NOT NULL DEFAULT '0',
  PRIMARY KEY (`text_id`),
  KEY `FKajfdd0f3dx7rgrt928uxc4glb` (`event_id`),
  KEY `FKaqajhefbhu4etl0q4dky99k6y` (`language_id`),
  CONSTRAINT `FKajfdd0f3dx7rgrt928uxc4glb` FOREIGN KEY (`event_id`) REFERENCES `tbl_notification_events` (`event_id`),
  CONSTRAINT `FKhrhsefhebr24wehdbnsc` FOREIGN KEY (`language_id`) REFERENCES `tbl_languages` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_notification_texts`
--

LOCK TABLES `tbl_notification_texts` WRITE;
/*!40000 ALTER TABLE `tbl_notification_texts` DISABLE KEYS */;
INSERT INTO `tbl_notification_texts` VALUES (1,1,1,'EEE User \"$USER_NAME$\" has been added by \"$USERNAME$\".'),(2,2,1,'EEE User \"$USER_NAME$\" has been updated by \"$USERNAME$\".'),(3,3,1,'EEE User \"$USER_NAME$\" has been removed by \"$USERNAME$\".'),(4,1,2,'FFF User \"$USER_NAME$\" has been added by \"$USERNAME$\".'),(5,2,2,'FFF User \"$USER_NAME$\" has been updated by \"$USERNAME$\".'),(6,3,2,'FFF User \"$USER_NAME$\" has been removed by \"$USERNAME$\".'),(7,1,3,'AAA User \"$USER_NAME$\" has been added by \"$USERNAME$\".'),(8,2,3,'AAA User \"$USER_NAME$\" has been updated by \"$USERNAME$\".'),(9,3,3,'AAA User \"$USER_NAME$\" has been removed by \"$USERNAME$\".');
/*!40000 ALTER TABLE `tbl_notification_texts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pages`
--

DROP TABLE IF EXISTS `tbl_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pages`
--

LOCK TABLES `tbl_pages` WRITE;
/*!40000 ALTER TABLE `tbl_pages` DISABLE KEYS */;
INSERT INTO `tbl_pages` VALUES (1,'DashBoard'),(2,'Settings');
/*!40000 ALTER TABLE `tbl_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pages_labels`
--

DROP TABLE IF EXISTS `tbl_pages_labels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pages_labels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL,
  `label_id` int(11) NOT NULL,
  `label_name` varchar(45) DEFAULT NULL,
  `lang_id` int(11) NOT NULL,
  `label` varchar(45) NOT NULL,
  `label_level` int(11) NOT NULL DEFAULT '1',
  `index_legend` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pages_labels`
--

LOCK TABLES `tbl_pages_labels` WRITE;
/*!40000 ALTER TABLE `tbl_pages_labels` DISABLE KEYS */;
INSERT INTO `tbl_pages_labels` VALUES (1,2,1,NULL,1,'SETTINGS ID',2,NULL),(2,2,2,NULL,1,'MSISDN LENGTH',2,NULL),(3,2,1,NULL,1,'General',1,NULL),(4,2,2,NULL,1,'LOGIN AUTHENTICATION',1,NULL),(5,2,1,NULL,2,'General FFF',1,NULL),(6,2,2,NULL,2,'LOGIN AUTHENTICATION FFF',1,NULL),(7,2,2,NULL,2,'MSISDN LENGTH FFF',2,NULL),(8,2,2,NULL,3,'MSISDN LENGTH AAA',2,NULL),(9,2,2,NULL,3,'LOGIN AUTHENTICATION AAA',1,NULL),(10,2,3,NULL,1,'LOCK ACCOUNT DURATION IN MINUTES EEE',2,NULL),(11,2,3,NULL,2,'LOCK ACCOUNT DURATION IN MINUTES FFF',2,NULL),(12,2,3,NULL,3,'LOCK ACCOUNT DURATION IN MINUTES AAA',2,NULL),(174,2,4,NULL,1,'NUMBER OF ATTEMPTS BETWEEN LOGIN IN SECONDS E',2,NULL),(175,2,4,NULL,2,'NUMBER OF ATTEMPTS BETWEEN LOGIN IN SECONDS F',2,NULL),(176,2,4,NULL,3,'NUMBER OF ATTEMPTS BETWEEN LOGIN IN SECONDS A',2,NULL),(177,2,5,NULL,1,'NUMBER OF ATTEMPTS PER LOGIN FAIL EEE',2,NULL),(178,2,5,NULL,2,'NUMBER OF ATTEMPTS PER LOGIN FAIL FFF',2,NULL),(179,2,5,NULL,3,'NUMBER OF ATTEMPTS PER LOGIN FAIL AAA',2,NULL),(180,2,1,NULL,3,'General AAA',1,NULL),(181,2,6,NULL,1,'WEB_URL',2,NULL);
/*!40000 ALTER TABLE `tbl_pages_labels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_persistent_login`
--

DROP TABLE IF EXISTS `tbl_persistent_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_persistent_login` (
  `SERIES` varchar(256) DEFAULT NULL,
  `USERNAME` varchar(256) DEFAULT NULL,
  `TOKEN` varchar(256) DEFAULT NULL,
  `LAST_USED` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_persistent_login`
--

LOCK TABLES `tbl_persistent_login` WRITE;
/*!40000 ALTER TABLE `tbl_persistent_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_persistent_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_profile`
--

DROP TABLE IF EXISTS `tbl_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_profile` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(51) NOT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `ABOUT` longtext,
  `IMAGE_PATH` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_profile`
--

LOCK TABLES `tbl_profile` WRITE;
/*!40000 ALTER TABLE `tbl_profile` DISABLE KEYS */;
INSERT INTO `tbl_profile` VALUES (1,'Omar Rajeh','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(2,'Ghida Hachicho','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(3,'Hiva Sedaghat','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(4,'Bassam Abou Diab','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(14,'Guy Nader','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(15,'Shaun Parker & Company','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(16,'Ivo Dimchev','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(17,'Cie Linga','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(18,'KEDA','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(19,'Cie Samuel Mathieu','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(20,'Yara Boustany','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(21,'MARCO CANTALUPO','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(22,'Nicolas Khoury','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(23,'HAMDI DRIDI','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(24,'STEPHANIE KAYAL','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(25,'JADD TANK','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(26,'NIVINE KALLAS','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(27,'HIVA DEDAGHAT','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(28,'MITRA ZIAEE KIA','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(29,'CHARLIE PRINCE','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(30,'BASSAM ABOU DIAB','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(31,'GACIA TOKAJIAN','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(32,'CYNTHIA TOJAKIAN','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(33,'RAMZ SAYYAM','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(34,'BIPOD','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(35,'Mitra Ziaee Kia','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(36,'Jacopo Jenna','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(37,'Maria Campos','2019-04-01 16:20:00',NULL,NULL,NULL,NULL),(38,'KEDA','2019-04-01 16:20:00',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tbl_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_profile_media`
--

DROP TABLE IF EXISTS `tbl_profile_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_profile_media` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PROFILE` bigint(20) NOT NULL,
  `NAME` varchar(21) NOT NULL,
  `PATH` varchar(1001) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_1435643523475672351122_idx` (`PROFILE`),
  CONSTRAINT `FK_1435643523475672351122` FOREIGN KEY (`PROFILE`) REFERENCES `tbl_profile` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_profile_media`
--

LOCK TABLES `tbl_profile_media` WRITE;
/*!40000 ALTER TABLE `tbl_profile_media` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_profile_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reports`
--

DROP TABLE IF EXISTS `tbl_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reports` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `proc_name` longtext,
  `last_query_contains_where` tinyint(4) NOT NULL DEFAULT '0',
  `last_query_contains_order` tinyint(4) NOT NULL DEFAULT '0',
  `last_query_contains_group` tinyint(4) NOT NULL DEFAULT '0',
  `chart_title` varchar(300) DEFAULT NULL,
  `chart_subtitle` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reports`
--

LOCK TABLES `tbl_reports` WRITE;
/*!40000 ALTER TABLE `tbl_reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reports_filter`
--

DROP TABLE IF EXISTS `tbl_reports_filter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reports_filter` (
  `id` bigint(20) NOT NULL,
  `report_field` varchar(45) DEFAULT NULL,
  `field_type` varchar(45) DEFAULT NULL,
  `report_id` bigint(20) DEFAULT NULL,
  `display_name` varchar(45) DEFAULT NULL,
  `select_query` longtext,
  `field_index` varchar(45) DEFAULT NULL,
  `required` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `gdfgsdfs_idx` (`report_id`),
  CONSTRAINT `fk_report_id` FOREIGN KEY (`report_id`) REFERENCES `tbl_reports` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reports_filter`
--

LOCK TABLES `tbl_reports_filter` WRITE;
/*!40000 ALTER TABLE `tbl_reports_filter` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_reports_filter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reports_style`
--

DROP TABLE IF EXISTS `tbl_reports_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reports_style` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reports_style`
--

LOCK TABLES `tbl_reports_style` WRITE;
/*!40000 ALTER TABLE `tbl_reports_style` DISABLE KEYS */;
INSERT INTO `tbl_reports_style` VALUES (1,'Table'),(2,'Pie Chart'),(3,'Bar Chart'),(4,'Line Chart');
/*!40000 ALTER TABLE `tbl_reports_style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reports_style_join`
--

DROP TABLE IF EXISTS `tbl_reports_style_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reports_style_join` (
  `report_id` bigint(20) NOT NULL,
  `report_style_id` int(11) NOT NULL,
  KEY `fk_report_style_id_idx` (`report_style_id`),
  KEY `fk_report_style_idasdwefs_idx` (`report_id`),
  CONSTRAINT `fk_report_style_id` FOREIGN KEY (`report_style_id`) REFERENCES `tbl_reports_style` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_report_style_idxx` FOREIGN KEY (`report_id`) REFERENCES `tbl_reports` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reports_style_join`
--

LOCK TABLES `tbl_reports_style_join` WRITE;
/*!40000 ALTER TABLE `tbl_reports_style_join` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_reports_style_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_roles`
--

DROP TABLE IF EXISTS `tbl_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_roles` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(256) DEFAULT NULL,
  `IS_SYSTEM_ROLE` tinyint(4) DEFAULT '1',
  `ROLE_LABEL` varchar(256) DEFAULT 'Default',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_roles`
--

LOCK TABLES `tbl_roles` WRITE;
/*!40000 ALTER TABLE `tbl_roles` DISABLE KEYS */;
INSERT INTO `tbl_roles` VALUES (1,'INSTALLER',0,'Installer role. This role is hidden to the user.'),(2,'SUPPORT',0,'Support role. This role is hidden to the user.'),(3,'VIEW_REPORTS',1,'View Reports'),(4,'VIEW_USERS',1,'View System Users'),(5,'ADD_USERS',1,'Add System Users'),(6,'EDIT_USERS',1,'Edit System Users'),(7,'DELETE_USERS',1,'Delete System Users'),(8,'VIEW_DASHBOARD',1,'View Dashboard'),(9,'VIEW_GROUPS',1,'View Groups'),(10,'ADD_GROUPS',1,'Add Groups'),(11,'EDIT_GROUPS',1,'Edit Groups'),(12,'DELETE_GROUPS',1,'Delete Groups'),(13,'VIEW_SETTINGS',1,'View Settings'),(14,'ADD_SETTINGS',1,'Add Settings'),(15,'EDIT_SETTINGS',1,'Edit Settings'),(16,'DELETE_SETTINGS',1,'Delete Settings'),(17,'VIEW_BLACKLISTS',1,'View Blacklist'),(18,'ADD_BLACKLISTS',1,'Add Blacklist'),(19,'DELETE_BLACKLISTS',1,'Delete Blacklist'),(20,'SYSTEM',1,'SYSTEM'),(21,'USER',1,'COMPANY'),(22,'OUR_SYSTEM_USER',1,'OUR_SYSTEM_USER');
/*!40000 ALTER TABLE `tbl_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_settings`
--

DROP TABLE IF EXISTS `tbl_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_settings` (
  `setting_id` int(11) NOT NULL AUTO_INCREMENT,
  `MSISDN_LENGTH` int(11) NOT NULL DEFAULT '0',
  `LOCK_ACCOUNT_DURATION` int(11) NOT NULL DEFAULT '0',
  `NUMBER_OF_SECONDS_BETWEEN_ATTEMPTS` int(11) NOT NULL DEFAULT '0',
  `NUMBER_OF_ATTEMPTS_PER_LOGIN_FAIL` int(11) NOT NULL DEFAULT '0',
  `WEB_URL` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_settings`
--

LOCK TABLES `tbl_settings` WRITE;
/*!40000 ALTER TABLE `tbl_settings` DISABLE KEYS */;
INSERT INTO `tbl_settings` VALUES (1,8,1,10,5,'http://35.229.113.142:8080/FreedomPass_Admin/#/sessions/changePassword?token=');
/*!40000 ALTER TABLE `tbl_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_settings_categories`
--

DROP TABLE IF EXISTS `tbl_settings_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_settings_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `display` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_settings_categories`
--

LOCK TABLES `tbl_settings_categories` WRITE;
/*!40000 ALTER TABLE `tbl_settings_categories` DISABLE KEYS */;
INSERT INTO `tbl_settings_categories` VALUES (1,'GENERAL',1),(2,'LOGIN AUTHENTICATION',1),(3,'CATEGORY_AUTOINC',0);
/*!40000 ALTER TABLE `tbl_settings_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_settings_mapping`
--

DROP TABLE IF EXISTS `tbl_settings_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_settings_mapping` (
  `COLUMNID` int(11) NOT NULL AUTO_INCREMENT,
  `COLUMNNAME` varchar(255) NOT NULL,
  `COLUMNDESCRIPTION` varchar(255) NOT NULL,
  `LABELDISPLAY` varchar(255) NOT NULL,
  `FIELDTYPE` varchar(255) NOT NULL,
  `RELATEDCOLUMNS` int(11) NOT NULL DEFAULT '0',
  `COLUMNVALUE` varchar(255) NOT NULL DEFAULT '',
  `QUERYTEXT` varchar(500) NOT NULL DEFAULT '',
  `ENABLED` int(11) NOT NULL DEFAULT '0',
  `EDITABLE` int(11) NOT NULL DEFAULT '0',
  `SUBTABLENAME` varchar(255) NOT NULL DEFAULT '',
  `AUTOINC` int(11) NOT NULL DEFAULT '0',
  `UNIQUEVALUE` int(11) NOT NULL DEFAULT '0',
  `MANDATORY` int(11) NOT NULL DEFAULT '0',
  `RELATEDCOLNAME` varchar(50) NOT NULL DEFAULT '',
  `RELATEDAUTOINCCOLNAME` varchar(50) NOT NULL DEFAULT '',
  `COLUMNCATEGORY` varchar(50) NOT NULL DEFAULT 'GENERAL',
  `ISADMIN` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COLUMNID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_settings_mapping`
--

LOCK TABLES `tbl_settings_mapping` WRITE;
/*!40000 ALTER TABLE `tbl_settings_mapping` DISABLE KEYS */;
INSERT INTO `tbl_settings_mapping` VALUES (1,'SETTING_ID','AUTO INC COLUMN','SETTINGS ID','NUMBER',0,'','',1,0,'',1,0,0,'','','CATEGORY_AUTOINC',0),(2,'MSISDN_LENGTH','MSISDN_LENGTH','MSISDN LENGTH','NUMBER',0,'','',1,2,'',0,0,1,'','','GENERAL',0),(3,'LOCK_ACCOUNT_DURATION','LOCK_ACCOUNT_DURATION','LOCK ACCOUNT DURATION IN MINUTES','NUMBER',0,'','',1,2,'',0,0,1,'','','LOGIN AUTHENTICATION',0),(4,'NUMBER_OF_SECONDS_BETWEEN_ATTEMPTS','NUMBER_OF_SECONDS_BETWEEN_ATTEMPTS','NUMBER OF ATTEMPTS BETWEEN LOGIN IN SECONDS','NUMBER',0,'','',1,2,'',0,0,1,'','','LOGIN AUTHENTICATION',0),(5,'NUMBER_OF_ATTEMPTS_PER_LOGIN_FAIL','NUMBER_OF_ATTEMPTS_PER_LOGIN_FAIL','NUMBER OF ATTEMPTS PER LOGIN FAIL','NUMBER',0,'','',1,2,'',0,0,1,'','','LOGIN AUTHENTICATION',0),(6,'WEB_URL','WEB_URL','WEB_URL','TEXT',0,'','',1,2,'',0,0,1,'','','GENERAL',0);
/*!40000 ALTER TABLE `tbl_settings_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_subscription`
--

DROP TABLE IF EXISTS `tbl_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_subscription` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(100) NOT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1555198433754471 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_subscription`
--

LOCK TABLES `tbl_subscription` WRITE;
/*!40000 ALTER TABLE `tbl_subscription` DISABLE KEYS */;
INSERT INTO `tbl_subscription` VALUES (1,'mahmoudmhdali@gmail.com','2019-03-24 19:45:52',NULL),(2,'rawad2k@gmail.com','2019-03-24 19:45:52',NULL),(3,'alaa@apliman.com','2019-03-24 19:45:52',NULL),(1553769090099093,'mahmoudmhdali@gmail.com1','2019-03-28 09:59:00',NULL),(1554157119642793,'rawad2k@gmail.com1','2019-04-01 21:45:07',NULL),(1554161854184413,'aa@qq','2019-04-01 23:27:48',NULL),(1554162370882194,'aaa@www','2019-04-01 23:30:03',NULL),(1554162475907850,'aaa@aaa','2019-04-01 23:22:36',NULL),(1554196130129515,'alaah.ashkar@gmail.com','2019-04-02 08:46:54',NULL),(1555187301208043,'becharafayad@hotmail.com','2019-04-13 19:57:48',NULL),(1555188130487925,'joe@achkar.com','2019-04-13 20:32:19',NULL),(1555198433754470,'nairiyan@ymail.com','2019-04-13 23:08:21',NULL);
/*!40000 ALTER TABLE `tbl_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user_attempts`
--

DROP TABLE IF EXISTS `tbl_user_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_attempts` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_PROFILE_ID` bigint(20) NOT NULL,
  `ATTEMPTS` smallint(1) NOT NULL,
  `LAST_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `USER_ATTEMPTS_FK1` (`USER_PROFILE_ID`),
  CONSTRAINT `USER_ATTEMPTS_FK1` FOREIGN KEY (`USER_PROFILE_ID`) REFERENCES `tbl_user_profiles` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1556125680309203 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user_attempts`
--

LOCK TABLES `tbl_user_attempts` WRITE;
/*!40000 ALTER TABLE `tbl_user_attempts` DISABLE KEYS */;
INSERT INTO `tbl_user_attempts` VALUES (1,1,0,'2018-09-27 12:43:24');
/*!40000 ALTER TABLE `tbl_user_attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user_profile_groups`
--

DROP TABLE IF EXISTS `tbl_user_profile_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_profile_groups` (
  `USER_PROFILE_ID` bigint(20) NOT NULL,
  `GROUP_ID` bigint(20) NOT NULL,
  KEY `fk_user_profile` (`USER_PROFILE_ID`),
  KEY `fk_group` (`GROUP_ID`),
  CONSTRAINT `fk_group` FOREIGN KEY (`GROUP_ID`) REFERENCES `tbl_groups` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_profile` FOREIGN KEY (`USER_PROFILE_ID`) REFERENCES `tbl_user_profiles` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user_profile_groups`
--

LOCK TABLES `tbl_user_profile_groups` WRITE;
/*!40000 ALTER TABLE `tbl_user_profile_groups` DISABLE KEYS */;
INSERT INTO `tbl_user_profile_groups` VALUES (1,3);
/*!40000 ALTER TABLE `tbl_user_profile_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user_profiles`
--

DROP TABLE IF EXISTS `tbl_user_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_profiles` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(64) NOT NULL,
  `EMAIL` varchar(256) NOT NULL,
  `PASSWORD` varchar(256) NOT NULL,
  `ENABLED` tinyint(4) NOT NULL DEFAULT '1',
  `ACCOUNT_EXPIRED` tinyint(4) NOT NULL DEFAULT '0',
  `ACCOUNT_LOCKED` tinyint(4) NOT NULL DEFAULT '0',
  `CREDENTIAL_EXPIRED` tinyint(4) NOT NULL DEFAULT '0',
  `CREATED_DATE` timestamp NULL DEFAULT NULL,
  `RESET_PASSWORD_TOKEN` varchar(200) DEFAULT NULL,
  `RESET_PASSWORD_TOKEN_VALIDITY` timestamp NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `JOB_TITLE` varchar(64) DEFAULT NULL,
  `LANGUAGE_ID` int(11) DEFAULT '1',
  `LAST_NAME` varchar(64) DEFAULT NULL,
  `TYPE` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_LANGUAGE_USER_idx` (`LANGUAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user_profiles`
--

LOCK TABLES `tbl_user_profiles` WRITE;
/*!40000 ALTER TABLE `tbl_user_profiles` DISABLE KEYS */;
INSERT INTO `tbl_user_profiles` VALUES (1,'System User','sysuser@apliman.com','$2a$10$Wet4W6yJnVM6bU8QFX75kuNoxGXnbx/kk5oJI05FAWTIYEpS.Ufc2',1,1,1,1,'2018-01-22 12:53:01',NULL,NULL,'2018-09-27 12:44:25',NULL,'System',1,'',99);
/*!40000 ALTER TABLE `tbl_user_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_userprofile_notification_event`
--

DROP TABLE IF EXISTS `tbl_userprofile_notification_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_userprofile_notification_event` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOTIFICATION_EVENT_ID` int(11) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKhbfuhas87y23rhjfhfew734rfs_idx` (`NOTIFICATION_EVENT_ID`),
  KEY `FKhbfuybajsnbf8278rwjnsf87wfs_idx` (`USER_ID`),
  CONSTRAINT `FKhbfuhas87y23rhjfhfew734rfs` FOREIGN KEY (`NOTIFICATION_EVENT_ID`) REFERENCES `tbl_notification_events` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKhbfuybajsnbf8278rwjnsf87wfs` FOREIGN KEY (`USER_ID`) REFERENCES `tbl_user_profiles` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_userprofile_notification_event`
--

LOCK TABLES `tbl_userprofile_notification_event` WRITE;
/*!40000 ALTER TABLE `tbl_userprofile_notification_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_userprofile_notification_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_web_notifications`
--

DROP TABLE IF EXISTS `tbl_web_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_web_notifications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL,
  `seen_flag` tinyint(4) NOT NULL DEFAULT '0',
  `text` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9k06vkj2qt5sekqw657j059tp` (`user_id`),
  CONSTRAINT `fk_userId1234124` FOREIGN KEY (`user_id`) REFERENCES `tbl_user_profiles` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_web_notifications`
--

LOCK TABLES `tbl_web_notifications` WRITE;
/*!40000 ALTER TABLE `tbl_web_notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_web_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_citerne_app'
--

--
-- Dumping routines for database 'db_citerne_app'
--
/*!50003 DROP PROCEDURE IF EXISTS `PROC_SELECTANYQUERY` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_SELECTANYQUERY`(IN QRY VARCHAR(1000))
BEGIN

	SET @QRY = QRY;

	PREPARE selectqry  FROM @QRY; 

	EXECUTE selectqry;

	DEALLOCATE PREPARE selectqry;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PROC_SELECTSETTINGSMAP` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_SELECTSETTINGSMAP`(IN B_ID INT, IN COL_ID INT, IN TBL_NAME VARCHAR(50), IN CATEGORY_NAME VARCHAR(50), IN IN_IsAdmin INT, IN_LANGID INT)
PROC_LABEL: BEGIN

	DECLARE TEMP_TBLNAME, TEMP_COLVALUES, CURRENT_VALUE VARCHAR(500) DEFAULT ''; 

    DECLARE CUR1_COUNT, temp, I INTEGER;

    

    DECLARE CUR1_COLUMNID INT(11);

	DECLARE CUR1_COLUMNNAME VARCHAR(255);

	DECLARE CUR1_COLUMNDESCRIPTION VARCHAR(255) ;

	DECLARE CUR1_LABELDISPLAY VARCHAR(255) ;

	DECLARE CUR1_FIELDTYPE VARCHAR(255) ;

	DECLARE CUR1_RELATEDCOLUMNS INTEGER;

	DECLARE CUR1_COLUMNVALUE VARCHAR(255); 

	DECLARE CUR1_QUERYTEXT VARCHAR(500);

	DECLARE CUR1_ENABLED	INT(11) ;

	DECLARE CUR1_EDITABLE INT(11) ;

	DECLARE CUR1_SUBTABLENAME VARCHAR(255);

	DECLARE CUR1_AUTOINC	INT(11);

	DECLARE CUR1_UNIQUEVALUE INT(11);

	DECLARE CUR1_MANDATORY INT(11);

	DECLARE CUR1_RELATEDCOLNAME VARCHAR(255);

	DECLARE CUR1_RELATEDAUTOINCCOLNAME VARCHAR(255);

	DECLARE CUR1_COLUMNCATEGORY VARCHAR(255);

    DECLARE CUR1_ISADMIN INT(11);

    DECLARE X VARCHAR(50);

    DECLARE done boolean DEFAULT FALSE;



    

    

	

	

		

		

		

	

	DECLARE CUR1 CURSOR FOR SELECT COLUMNID , COLUMNNAME , COLUMNDESCRIPTION  , 

    (CASE WHEN (SELECT LABEL FROM TBL_PAGES_LABELS WHERE PAGE_ID=2 AND LABEL_LEVEL=2 AND LABEL_ID=COLUMNID AND LANG_ID=IN_LANGID) IS NOT NULL THEN (SELECT LABEL FROM TBL_PAGES_LABELS WHERE PAGE_ID=2 AND LABEL_LEVEL=2 AND LABEL_ID=COLUMNID AND LANG_ID=IN_LANGID) ELSE LABELDISPLAY END) AS LABELDISPLAY , FIELDTYPE, RELATEDCOLUMNS , COLUMNVALUE , QUERYTEXT , ENABLED, EDITABLE, SUBTABLENAME , AUTOINC	, UNIQUEVALUE , MANDATORY , RELATEDCOLNAME , RELATEDAUTOINCCOLNAME , COLUMNCATEGORY, ISADMIN FROM TBL_SETTINGS_MAPPING WHERE ENABLED=1 AND RELATEDCOLUMNS=COL_ID ORDER BY COLUMNID ASC;

	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = true;

   

    SELECT COUNT(1) INTO CUR1_COUNT FROM TBL_SETTINGS_MAPPING WHERE ENABLED=1 AND RELATEDCOLUMNS=COL_ID;

    

    

    SELECT CONCAT('TEMP_SETTINGS_MAPPING_', REPLACE(UUID(),"-", "")) INTO TEMP_TBLNAME;

	set @createtable = CONCAT('CREATE TEMPORARY TABLE  ', TEMP_TBLNAME, ' LIKE  TBL_SETTINGS_MAPPING ');

	PREPARE execquery FROM @createtable ; set @createtable = ''; EXECUTE execquery; DEALLOCATE PREPARE execquery ; 

	

    SET @createtable = CONCAT('ALTER TABLE ', TEMP_TBLNAME, ' ENGINE = MEMORY ');

    PREPARE execquery FROM @createtable ; set @createtable = ''; EXECUTE execquery; DEALLOCATE PREPARE execquery ;

    

    SET I =0;

	SET @inserttbl = CONCAT('INSERT INTO ', TEMP_TBLNAME , ' VALUES ');

    OPEN CUR1;

    loop_settings_mapping: LOOP

    FETCH CUR1 into CUR1_COLUMNID, CUR1_COLUMNNAME, CUR1_COLUMNDESCRIPTION, CUR1_LABELDISPLAY, CUR1_FIELDTYPE, CUR1_RELATEDCOLUMNS, CUR1_COLUMNVALUE, CUR1_QUERYTEXT, CUR1_ENABLED, CUR1_EDITABLE, CUR1_SUBTABLENAME, CUR1_AUTOINC, CUR1_UNIQUEVALUE, CUR1_MANDATORY, CUR1_RELATEDCOLNAME, CUR1_RELATEDAUTOINCCOLNAME, CUR1_COLUMNCATEGORY, CUR1_ISADMIN;

        IF CUR1_COUNT <=0 THEN

			LEAVE loop_settings_mapping;

		END IF;

        IF (IN_IsAdmin=1) OR (IN_IsAdmin=0 AND CUR1_ISADMIN=0) THEN

			IF LOWER(CATEGORY_NAME) = 'all' OR LOWER(CATEGORY_NAME) = LOWER(CUR1_COLUMNCATEGORY) OR LOWER(CUR1_COLUMNCATEGORY) = 'category_autoinc' THEN

				IF B_ID !=0 THEN

					IF COL_ID = 0 THEN

						

						SET CURRENT_VALUE = CONCAT('SELECT ', CUR1_COLUMNNAME, ' FROM ', TBL_NAME, ' WHERE SETTING_ID=',B_ID);

						

						

					ELSE

						SET CURRENT_VALUE = '""';

					END IF;

				END IF;

                IF I > 0 THEN

					SET TEMP_COLVALUES = ',(';

                    

				ELSE

					SET TEMP_COLVALUES = '(';

                END IF;

				

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_COLUMNID, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_COLUMNNAME, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_COLUMNDESCRIPTION, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_LABELDISPLAY, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_FIELDTYPE, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_RELATEDCOLUMNS, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '(',  CURRENT_VALUE, '), ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_QUERYTEXT, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_ENABLED, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_EDITABLE, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_SUBTABLENAME, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_AUTOINC, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_UNIQUEVALUE, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_MANDATORY, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_RELATEDCOLNAME, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_RELATEDAUTOINCCOLNAME, '"', ', ');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_COLUMNCATEGORY, '"', ', ');

                SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, '"', CUR1_ISADMIN, '"');

				SET TEMP_COLVALUES = CONCAT(TEMP_COLVALUES, ')');

                SET @inserttbl = CONCAT(@inserttbl, TEMP_COLVALUES);

			END IF;

        END IF;

        SET I = I + 1;

        SET CUR1_COUNT = CUR1_COUNT -1;

	END LOOP;

    CLOSE CUR1;

    

    PREPARE execquery FROM @inserttbl ; SET @inserttbl =''; EXECUTE execquery; DEALLOCATE PREPARE execquery ;

    SET @selecttbl  = CONCAT('SELECT * FROM ', TEMP_TBLNAME);

    PREPARE execquery FROM @selecttbl ; SET @selecttbl  =''; EXECUTE execquery; DEALLOCATE PREPARE execquery ;

END PROC_LABEL ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reportsProcedure` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportsProcedure`(IN sqlString LONGTEXT, IN withCounter TINYINT(1), IN pageNumber INT, limitPerPage INT, IN withLimit TINYINT(1), OUT rowCount INT(11))
BEGIN

	DECLARE startIndex INT DEFAULT ((pageNumber - 1) * limitPerPage);



	IF withLimit=1 THEN

		IF withCounter=1 THEN

			SET @SQLStringCount = CONCAT('SELECT COUNT(*) INTO @rowCount FROM (', sqlString, ') as counter');

			PREPARE countSTMT FROM @SQLStringCount;

			EXECUTE countSTMT;

			SELECT @rowCount INTO rowCount;

		END IF;



		SET @SQLStringRows = CONCAT(sqlString, ' LIMIT ?,?');

		PREPARE rowsSTMT FROM @SQLStringRows;

		SET @FROM = startIndex;

		SET @TO = limitPerPage;

		EXECUTE rowsSTMT USING @FROM, @TO;

	END IF;



	IF withLimit=0 THEN

		SET @SQLStringRows = CONCAT(sqlString);

		PREPARE rowsSTMT FROM @SQLStringRows;

		EXECUTE rowsSTMT;

	END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `resetAll` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `resetAll`()
BEGIN

SET SQL_SAFE_UPDATES = 0;

delete from tbl_admin_passes_outlet_offers;

delete from tbl_user_company_passes;

delete from tbl_user_pass_purchased;

delete from tbl_admin_passes;

delete from tbl_user_attempts where id != 1;

delete from tbl_user_company_info_images;

delete from tbl_user_company_info_locations;

delete from tbl_user_company_info;

delete from tbl_user_outlet_info_category;

delete from tbl_user_outlet_info_images;

delete from tbl_user_outlet_info_locations;

delete from tbl_user_outlet_offer_images;

delete from tbl_user_outlet_offer_used;

delete from tbl_user_profile_groups where user_profile_id != 1;

delete from tbl_user_outlet_offer;

delete from tbl_user_outlet_info;

delete from tbl_user_profiles where id !=1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-30 17:05:54
