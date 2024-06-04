Table student {
  id integer [primary key]
  first_name varchar
  last_name varchar
  student_id integer
  mobile_number varchar
  school_name varchar
  status boolean
  created_at timestamp
  updated_at timestamp
}

Table fees {
  id integer [primary key]
  fk_student_id integer
  grade integer
  amount float
  currency varchar
  reference_id bigint
  card_number varchar
  card_type varchar
  date timestamp
  created_at timestamp
  updated_at timestamp
}


Ref: fees.fk_student_id > student.id 