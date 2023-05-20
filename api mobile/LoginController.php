<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Validator;
use App\Models\data_siswa;
use Illuminate\Support\Str;

class LoginController extends Controller
{
    public function login(Request $request)
    {
        // $nis = $request->input('NIS');
        // $password = $request->input('password');

            

        // if ($user) {
        //     return response()->json([
        //         'status' => 'Success',
        //         'message' => 'Login Berhasil',
        //         'data' => $user
        //     ]);
        // } else {
        //     return response()->json([
        //         'status' => 'Error',
        //         'message' => 'Login Gagal'
        //     ], 401);
        // }
        $login = Auth::guard('mobile')->attempt($request->all());
        if ($login) {
            $user = Auth::guard('mobile')->user();
            $user->api_token = Str::random(100);
            $user->save();
            // $user->makeVisible('api_token');
            

            return response()->json([
                'status' => true,
                'message' => 'Login Berhasil',
                'data' => $user
            ]);
        }else{
            return response()->json([
                'status' => false,
                'message' => 'Login Gagal',
            ]);
    }
}

}