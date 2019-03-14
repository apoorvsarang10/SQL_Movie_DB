create table movies(
  id varchar(16) primary key,
  title varchar(255),
  imdbID varchar(16),
  spanishTitle varchar(255),
  imdbPictureURL varchar(255),
  year integer,
  rtID varchar(255),
  rtAllCriticsRating number,
  rtAllCriticsNumReviews number,
  rtAllCriticsNumFresh number,
  rtAllCriticsNumRotten number,
  rtAllCriticsScore number,
  rtTopCriticsRating number,
  rtTopCriticsNumReviews number,
  rtTopCriticsNumFresh number,
  rtTopCriticsNumRotten number,
  rtTopCriticsScore number,
  rtAudienceRating number,
  rtAudienceNumRatings number,
  rtAudienceScore number,
  rtPictureURL varchar(255)
);

create table tags(
  id varchar(16) PRIMARY KEY, 
  value varchar(255)
);

create table movie_genres(
  movieID varchar(16), 
  genre varchar(40) NOT NULL,
  PRIMARY KEY (movieID, genre),
  FOREIGN KEY (movieID) REFERENCES movies(id)
);

create table movie_locations(
  movieID varchar(16),
  location1 varchar(255),
  location2 varchar(255),
  location3 varchar(255),
  location4 varchar(255),
  FOREIGN KEY (movieID) REFERENCES movies(id)  
);

create table movie_tags(
  movieID varchar(16),
  tagID varchar(16) NOT NULL,
  tagWeight number NOT NUll,
  PRIMARY KEY (movieID,tagID,tagWeight),
  FOREIGN KEY (movieID) REFERENCES movies(id),
  FOREIGN KEY (tagID) REFERENCES tags(id)
);

create table movie_countries(
movieID varchar(16) PRIMARY KEY, 
country varchar(255),
FOREIGN KEY (movieID) REFERENCES movies(id)  
);

create table movie_actors(
  movieID varchar(16) NOT NULL,
  actorID varchar(255) NOT NULL,
  actorName varchar(255),
  ranking integer,
  PRIMARY KEY (movieID,actorID),
  FOREIGN KEY (movieID) REFERENCES movies(id)  
);

create table movie_directors(
  movieID varchar(16) NOT NULL,
  directorID varchar(255) NOT NULL,
  directorName varchar(255),
  PRIMARY KEY (movieID,directorID),
  FOREIGN KEY (movieID) REFERENCES movies(id)  
);

create table user_ratedmovies(
  userId varchar(16) NOT NULL,
  movieID varchar(16) NOT NULL,
  rating NUMBER NOT NULL,
  date_day NUMBER(2,0),
  date_month NUMBER(2,0),
  date_year  NUMBER(4,0),
  date_hour  NUMBER(2,0),
  date_minute  NUMBER(2,0),
  date_second  NUMBER(2,0),
  PRIMARY KEY (userId,movieID),
  FOREIGN KEY (movieID) REFERENCES movies(id)
);

create table user_ratedmovies_timestamp(
  userId varchar(16) NOT NULL,
  movieID varchar(16) NOT NULL,
  rating NUMBER NOT NULL,
  timestamp char(13),
  PRIMARY KEY (userId,movieID),
  FOREIGN KEY (movieID) REFERENCES movies(id)
);

create table user_taggedmovies(
  userId varchar(16) NOT NULL,
  movieID varchar(16) NOT NULL,
  tagID varchar(16) NOT NULL,
  date_day NUMBER(2,0),
  date_month NUMBER(2,0),
  date_year  NUMBER(4,0),
  date_hour  NUMBER(2,0),
  date_minute  NUMBER(2,0),
  date_second  NUMBER(2,0),
  PRIMARY KEY (userId,movieID,tagID),
  FOREIGN KEY (movieID) REFERENCES movies(id),
  FOREIGN KEY (tagID) REFERENCES tags(id)
);

create table user_taggedmovies_timestamp(
  userId varchar(16) NOT NULL,
  movieID varchar(16) NOT NULL,
  tagID varchar(16) NOT NULL,
  timestamp char(13),
  PRIMARY KEY (userId,movieID,tagID),
  FOREIGN KEY (movieID) REFERENCES movies(id),
  FOREIGN KEY (tagID) REFERENCES tags(id)
);


create index title_index on movies(title);
create index countries_index on movie_countries(country);
create index genre_index on movie_genres(genre);
create index actorName_index on movie_actors(actorName);
create index directorName_index on movie_directors(directorName);
create index locations_index1 on movie_locations(location1);
create index locations_index2 on movie_locations(location2);
create index locations_index3 on movie_locations(location3);
create index locations_index4 on movie_locations(location4);

