import React, { useState } from 'react'
import { TbCircleDashed } from "react-icons/tb"
import { BiCommentDetail } from "react-icons/bi"
import { AiOutlineSearch } from "react-icons/ai"
import { BsEmojiSmile, BsFilter, BsMicFill, BsThreeDotsVertical } from "react-icons/bs"
import { ImAttachment } from "react-icons/im"
import ChatCard from './ChatCard/ChatCard'
import MessageCard from './MessageCard/MessageCard'
import "./HomePage.css"
import { useNavigate } from 'react-router-dom'
import Profile from './Profile'

const HomePage = () => {

    const [queries, setQueries] = useState(null);
    const [currentChat, setCurrentChat] = useState(null);
    const [content, setContent] = useState("");
    const [isProfile, setIsProfile] = useState(false);
    const navigate = useNavigate();

    const handleClickOnChatCard = () => {
        setCurrentChat(true)
    }

    const handleSearch = () => { }

    const handleCreateNewMessage = () => {
    }

    const handleNavigate = () => {

        setIsProfile(true);
    }

    return (
        <div className='relative'>
            <div className=' w-full py-14 bg-[#00a884] '></div>
            <div className='flex bg-[#f0f2f5] h-[90vh]  w-[96vw] absolute left-[2vw] top-[5vh] '>
                <div className='left w-[30%] bg-[#e8e9ec] h-full '>


                    {/* Profile */}
                    {isProfile && <Profile />}

                    {!isProfile &&
                        <div className='w-full'>


                            <div onClick={handleNavigate} className='flex justify-between items-center p-3'>

                                <div className=' flex items-center space-x-3'>
                                    <img className='rounded-full w-10 h-10 cursor-pointer'
                                        src='https://cdn.pixabay.com/photo/2023/08/18/15/02/cat-8198720_1280.jpg'
                                        alt='' />
                                    <p>username</p>
                                </div>
                                <div className='space-x-3 test-2xl flex'>
                                    <TbCircleDashed />
                                    <BiCommentDetail />
                                </div>
                            </div>


                            <div className=' relative flex justify-center items-center bg-white py-4 px-3'>
                                <input className=' border-none outline-none bg-slate-200 rounded-md w-[93%] pl-7 py-2'
                                    type='text'
                                    placeholder='Search or Start new Chat'
                                    onChange={(e) => {
                                        setQueries(e.target.value)
                                        handleSearch(e.target.value)
                                    }}
                                    value={queries}
                                />

                                <AiOutlineSearch className='left-4 top-7 absolute' />
                                <div>
                                    <BsFilter className=" ml-4 text-3xl" />
                                </div>

                            </div>

                            {/* all users */}
                            <div className=' bg-white overflow-y-scroll h-[72vh] px-3'>
                                {queries && [1, 1, 1, 1, 1, 1]
                                    .map((item) =>
                                        <div onClick={handleClickOnChatCard}>
                                            <hr />
                                            <ChatCard />
                                        </div>
                                    )}

                            </div>

                        </div>
                    }

                </div>

                {/* default page */}
                {!currentChat && <div className=' w-[70%] flex flex-col items-center justify-center h-full'>

                    <div className=' max-w-[70%] text-center '>
                        <img src='https://img.freepik.com/free-photo/colorful-mixed-rainbow-powder-explosion-isolated-white-background_90220-1048.jpg'
                            alt=''
                            className=' blur-sm'
                        />
                        <h1 className=' text-4xl text-gray-600 pt-2'>NetChat</h1>
                        <p className=' my-9'>Experience the effortless connection with NetChat's friendly and intuitive interface</p>

                    </div>
                </div>}

                {/* chat part */}

                {currentChat && <div className='w-[70%] relative  bg-blue-200'>

                    <div className=' header absolute top-0 w-full bg-[#f0f2f5]'>
                        <div className='flex justify-between'>
                            <div className=' py-3 space-x-3 flex items-center px-3'>
                                <img className=' w-10 h-10 rounded-full'
                                    src='https://cdn.pixabay.com/photo/2023/11/08/23/23/common-kingfisher-8376008_1280.jpg' alt='' />
                                <p>
                                    username
                                </p>
                            </div>
                            <div className=' py-3 space-x-3 flex items-center px-3 '>
                                <AiOutlineSearch />
                                <BsThreeDotsVertical />
                            </div>
                        </div>
                    </div>


                    {/* single message section */}
                    <div className=' px-10 h-[85%] overflow-y-scroll'>
                        <div className=' space-y-1 flex flex-col justify-center mt-20 py-2'>
                            {[1, 1, 1, 1, 1, 1].map((item, i) => <MessageCard isReqUserMessage={i % 2 === 0} content={"message"} />)}
                        </div>
                    </div>


                    {/* footer part */}
                    <div className=' footer bg-[#f0f2f5] absolute bottom-0 w-full py-3 text-2xl '>

                        <div className=' flex justify-between items-center px-2 relative '>

                            <BsEmojiSmile className=' cursor-pointer' />
                            <ImAttachment />


                            <input className=' text-lg py-2 outline-none border-none bg-white pl-4 rounded-md w-[85%] '
                                type='text' onChange={(e) => setContent(e.target.value)}
                                placeholder='Type Message'
                                value={content}
                                onKeyPress={(e) => {
                                    if (e.key === 'Enter') {
                                        handleCreateNewMessage();
                                        setContent("");
                                    }
                                }}
                            ></input>

                            <BsMicFill />

                        </div>

                    </div>


                </div>}

            </div>


        </div>
    )
}

export default HomePage
