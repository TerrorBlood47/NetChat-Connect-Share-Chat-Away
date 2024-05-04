
import React from 'react'

import { BsArrowLeft, BsPencil } from "react-icons/bs"
import { useNavigate } from 'react-router-dom'

const Profile = () => {

    const navigate = useNavigate();

    const handleNavigate = () => {
        navigate(-1);  //go back to where u came from
    }

    return (
        <div className=' h-full w-full'>
            <div className=' flex items-center space-x-10 bg-green-400 text-white pt-16 px-10 pb-5 '>
                <BsArrowLeft className=' cursor-pointer text-2xl font-bold' onClick={handleNavigate} />
                <p className=' cursor-pointer font-semibold'>Profile</p>
            </div>

            {/* update profile pic section */}
            <div className='flex flex-col justify-center items-center my-12'>
                <label htmlFor='imgInput'>
                    <img
                        className=' rounded-full w-[15vw] h-[15vw] cursor-pointer'
                        src='https://media.istockphoto.com/id/1477641490/photo/glad-asian-woman-touching-neck.jpg?s=2048x2048&w=is&k=20&c=qUKWL7lJMP77Q_GL7Za3THFlw8nTk6gPuT4IN8ezHzI='
                        alt=''
                    />
                </label>

                <input type='file' id='imgInput' className='hidden'></input>
            </div>


            {/* name section */}
            <div className=' bg-white px-3'>
                <p className=' py-3 '> Your Name </p>

                <div className=' w-full flex  justify-between items-center'>
                    <p className=' py-3'>username</p>
                    <BsPencil className=' cursor-pointer' />
                </div>

            </div>



        </div>
    )
}

export default Profile
